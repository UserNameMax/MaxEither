package ru.mishenko.maksim.either.factory

fun interface ReasonFactory<T> {
    operator fun invoke(e: Throwable): T
}