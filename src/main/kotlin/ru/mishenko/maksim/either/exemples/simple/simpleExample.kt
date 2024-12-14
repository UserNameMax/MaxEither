package ru.mishenko.maksim.either.exemples.simple

import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.getOrDefault
import ru.mishenko.maksim.either.onFailure
import ru.mishenko.maksim.either.onSuccess
import kotlin.random.Random

fun main() {
    repeat(5) {
        println("----------------------------")
        either { Random.nextInt() }
            .mapHandling {
                if (it % 2 == 0) returnSuccess(it) else returnFailure("bad value")
            }
            .onSuccess { println("call on success") }
            .onFailure { println("call on failure") }
            .recover { 8 }
            .getOrDefault("default value")
            .run { println(this) }
    }
}