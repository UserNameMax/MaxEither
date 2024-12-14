package ru.mishenko.maksim.either.factory

import ru.mishenko.maksim.either.Either

inline fun <E, T> either(handler: ReasonFactory<E>, block: () -> T) = try {
    Either.Success(block())
} catch (e: Throwable) {
    Either.Failure(handler(e))
}