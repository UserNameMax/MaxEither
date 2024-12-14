package ru.mishenko.maksim.either

sealed interface Either<out E, out D> {
    data class Failure<out E>(val reason: E) : Either<E, Nothing>
    data class Success<out D>(val data: D) : Either<Nothing, D>
}