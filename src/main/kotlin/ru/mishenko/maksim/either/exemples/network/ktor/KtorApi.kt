package ru.mishenko.maksim.either.exemples.network.ktor

import io.ktor.client.*
import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.exemples.network.app.PokeApi
import ru.mishenko.maksim.either.exemples.network.model.MyReason
import ru.mishenko.maksim.either.exemples.network.model.MyReasonFactory
import ru.mishenko.maksim.either.exemples.network.model.PokemonResponse
import ru.mishenko.maksim.either.networkUtils.ktor.KtorExtentions.body
import ru.mishenko.maksim.either.networkUtils.ktor.KtorExtentions.getCatching

class KtorApi(
    private val client: HttpClient,
): PokeApi {
    override suspend fun getPokemons(): Either<MyReason, PokemonResponse> =
        client
            .getCatching(MyReasonFactory, "https://pokeapi.co/api/v2/pokemon")
            .body()
}