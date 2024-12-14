package ru.mishenko.maksim.either.exemples.simple

import ru.mishenko.maksim.either.factory.ReasonFactory

class ReasonFactoryImpl : ReasonFactory<String> {
    override fun invoke(e: Throwable): String = "Some Error"
}