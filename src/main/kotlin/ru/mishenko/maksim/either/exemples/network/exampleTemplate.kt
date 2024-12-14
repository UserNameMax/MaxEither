package ru.mishenko.maksim.either.exemples.network

import ru.mishenko.maksim.either.exemples.network.app.GetPokeNamesUseCase.getList
import ru.mishenko.maksim.either.exemples.network.app.PokeApiFactory

suspend fun printPokeList(apiFactory: PokeApiFactory){
    println(apiFactory.getApi().getList())
}