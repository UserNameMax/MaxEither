package ru.mishenko.maksim.either

val Either<*, *>.isSuccess get() = this is Either.Success
val Either<*, *>.isFailure get() = this is Either.Failure

fun <E, D> Either<E, D>.getSuccessOrNull() = this as? Either.Success
fun <E, D> Either<E, D>.getFailureOrNull() = this as? Either.Failure

fun <E, D> Either<E, D>.getOrNull() = getSuccessOrNull()?.data
fun <E, D> Either<E, D>.reasonOrNull() = getFailureOrNull()?.reason

inline fun <E, D, T> Either<E, D>.fold(onSuccess: (D) -> T, onFailure: (E) -> T) =
    when (this) {
        is Either.Failure -> onFailure(reason)
        is Either.Success -> onSuccess(data)
    }

fun <E, D> Either<E, D>.getOrDefault(defaultValue: D) = getOrNull() ?: defaultValue
inline fun <E, D> Either<E, D>.getOrElse(onFailure: (E) -> D) = fold(onSuccess = { it }, onFailure = onFailure)
inline fun <E, D, T> Either<E, D>.map(transform: (D) -> T): Either<E, T> =
    fold(
        onSuccess = { Either.Success(transform(it)) },
        onFailure = { Either.Failure(it) }
    )

inline fun <E, D> Either<E, D>.onSuccess(action: (D) -> Unit) = apply {
    getOrNull()?.let { action(it) }
}

inline fun <E, D> Either<E, D>.onFailure(action: (E) -> Unit) = apply {
    reasonOrNull()?.let { action(it) }
}

fun <E, D> Either<E, Either<E, D>>.flatten(): Either<E, D> = fold(
    onSuccess = { it },
    onFailure = { Either.Failure(it) }
)
