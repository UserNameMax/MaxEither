package ru.mishenko.maksim.either.exemples.network.ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import ru.mishenko.maksim.either.exemples.network.app.PokeApi
import ru.mishenko.maksim.either.exemples.network.app.PokeApiFactory
import ru.mishenko.maksim.either.exemples.network.model.MyReasonFactory
import ru.mishenko.maksim.either.exemples.network.utils.Defaults.json
import ru.mishenko.maksim.either.networkUtils.ktor.either

object KtorApiFactory : PokeApiFactory {
    private fun client() = HttpClient(CIO) {
        install(
            either(
                json = json,
                reasonFactory = MyReasonFactory,
                networkReasonFactory = MyReasonFactory
            )
        )
    }

    override fun getApi(): PokeApi = KtorApi(client())
}