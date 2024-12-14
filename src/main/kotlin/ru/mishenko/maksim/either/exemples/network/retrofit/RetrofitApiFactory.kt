package ru.mishenko.maksim.either.exemples.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import ru.mishenko.maksim.either.exemples.network.app.PokeApi
import ru.mishenko.maksim.either.exemples.network.app.PokeApiFactory
import ru.mishenko.maksim.either.exemples.network.model.MyReasonFactory
import ru.mishenko.maksim.either.exemples.network.utils.Defaults.json
import ru.mishenko.maksim.either.networkUtils.retorfit.EitherCallAdapterFactory

object RetrofitApiFactory : PokeApiFactory {
    override fun getApi(): PokeApi =
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(EitherCallAdapterFactory(MyReasonFactory, MyReasonFactory))
            .build()
            .create<RetrofitApi>()
}