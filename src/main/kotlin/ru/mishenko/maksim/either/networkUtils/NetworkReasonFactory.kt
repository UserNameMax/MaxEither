package ru.mishenko.maksim.either.networkUtils

fun interface NetworkReasonFactory<T> {
    operator fun invoke(code: Int, data: String): T
}