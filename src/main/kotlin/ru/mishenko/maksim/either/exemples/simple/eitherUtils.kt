package ru.mishenko.maksim.either.exemples.simple

import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.handling.HandlingScope
import ru.mishenko.maksim.either.handling.mapHandling
import ru.mishenko.maksim.either.handling.recover
import ru.mishenko.maksim.either.factory.either

inline fun <D, T> Either<String, D>.mapHandling(transform: HandlingScope<String, T>.(D) -> T): Either<String, T> =
    mapHandling(ReasonFactoryImpl(), transform)

inline fun <D, T : D> Either<String, D>.recover(transform: HandlingScope<String, T>.(String) -> T): Either<String, D> =
    recover(ReasonFactoryImpl(), transform)

inline fun <T> either(block: () -> T): Either<String, T> = either(ReasonFactoryImpl(), block)