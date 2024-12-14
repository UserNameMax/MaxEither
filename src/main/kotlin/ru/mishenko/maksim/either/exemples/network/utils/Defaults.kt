package ru.mishenko.maksim.either.exemples.network.utils

import kotlinx.serialization.json.Json

object Defaults {
    val json = Json { ignoreUnknownKeys = true }
}