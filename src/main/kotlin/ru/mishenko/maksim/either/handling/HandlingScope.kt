package ru.mishenko.maksim.either.handling

import ru.mishenko.maksim.either.Either

class HandlingScope<E, D> {
    var result: Either<E, D>? = null
    fun returnSuccess(data: D): D {
        result = Either.Success(data)
        return data
    }

    fun returnFailure(reason: E): D? {
        result = Either.Failure(reason)
        return null
    }
}