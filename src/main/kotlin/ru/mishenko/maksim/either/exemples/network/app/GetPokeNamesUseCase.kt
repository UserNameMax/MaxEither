package ru.mishenko.maksim.either.exemples.network.app

import ru.mishenko.maksim.either.fold
import ru.mishenko.maksim.either.map

object GetPokeNamesUseCase {
    suspend fun PokeApi.getList() = getPokemons()
        .map { dto -> dto.results.map { pokemon -> pokemon.name } }
        .fold(
            onSuccess = { pokemons -> pokemons.joinToString() },
            onFailure = { reason -> reason.toString() }
        )
}