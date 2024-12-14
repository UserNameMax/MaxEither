package ru.mishenko.maksim.either.handling

import ru.mishenko.maksim.either.Either

class HandlingScope<E, D> {
    var result: Either<E, D>? = null
    fun returnSuccess(data: D) {
        result = Either.Success(data)
    }

    fun returnFailure(reason: E) {
        result = Either.Failure(reason)
    }
}