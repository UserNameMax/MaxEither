package ru.mishenko.maksim.either.exemples.network.model

sealed interface MyReason {
    data class NetworkReason(val code: Int, val body: String) : MyReason
    data class TextReason(val text: String) : MyReason
}