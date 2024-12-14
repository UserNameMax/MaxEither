package ru.mishenko.maksim.either.exemples.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(val results: List<Pokemon>)
