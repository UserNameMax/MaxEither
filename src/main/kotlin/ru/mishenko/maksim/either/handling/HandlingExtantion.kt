package ru.mishenko.maksim.either.handling

import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.factory.ReasonFactory
import ru.mishenko.maksim.either.factory.either
import ru.mishenko.maksim.either.flatten

inline fun <E, D, T> Either<E, D>.mapHandling(handler: ReasonFactory<E>, transform: HandlingScope<E, T>.(D) -> T?) =
    when (this) {
        is Either.Failure -> this
        is Either.Success -> either(handler) {
            val scope = HandlingScope<E, T>()
            val result = scope.run { transform(data) }
            scope.result ?: Either.Success(result!!)
        }.flatten()
    }

inline fun <E, D, T : D> Either<E, D>.recover(handler: ReasonFactory<E>, transform: HandlingScope<E, T>.(E) -> T?) =
    when (this) {
        is Either.Failure -> either(handler) {
            val scope = HandlingScope<E, T>()
            val result = scope.run { transform(reason) }
            scope.result ?: Either.Success(result!!)
        }.flatten()

        is Either.Success -> this
    }