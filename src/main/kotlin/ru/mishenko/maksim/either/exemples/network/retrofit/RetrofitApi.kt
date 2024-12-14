package ru.mishenko.maksim.either.exemples.network.retrofit

import retrofit2.http.GET
import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.exemples.network.app.PokeApi
import ru.mishenko.maksim.either.exemples.network.model.MyReason
import ru.mishenko.maksim.either.exemples.network.model.PokemonResponse

interface RetrofitApi: PokeApi {
    @GET("pokemon")
    override suspend fun getPokemons(): Either<MyReason, PokemonResponse>
}