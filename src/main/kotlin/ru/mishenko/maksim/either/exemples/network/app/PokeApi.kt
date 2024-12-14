package ru.mishenko.maksim.either.exemples.network.app

import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.exemples.network.model.MyReason
import ru.mishenko.maksim.either.exemples.network.model.PokemonResponse

interface PokeApi {
    suspend fun getPokemons(): Either<MyReason, PokemonResponse>
}