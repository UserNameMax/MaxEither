package ru.mishenko.maksim.either.networkUtils.ktor

import io.ktor.client.plugins.api.*
import io.ktor.http.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.factory.ReasonFactory
import ru.mishenko.maksim.either.factory.either
import ru.mishenko.maksim.either.flatten
import ru.mishenko.maksim.either.getOrNull
import ru.mishenko.maksim.either.handling.mapHandling
import ru.mishenko.maksim.either.networkUtils.NetworkReasonFactory
import kotlin.reflect.jvm.jvmErasure

fun <T> either(
    json: Json = Json,
    reasonFactory: ReasonFactory<T>,
    networkReasonFactory: NetworkReasonFactory<T>
) = createClientPlugin("ktorEitherPlugin") {
    transformResponseBody { response, content, requestedType ->
        if (requestedType.isEither()) {
            either(reasonFactory) {
                val textContent = content.decodeToString()
                if (response.status.isSuccess()) {
                    val serializer = requestedType.successTypeSerializer()
                    Either.Success(json.decodeFromString(serializer, textContent))
                } else {
                    Either.Failure(networkReasonFactory(response.status.value, textContent))
                }
            }.flatten()
        } else {
            null
        }
    }
}

private fun ByteReadChannel.decodeToString() = toInputStream().readAllBytes().decodeToString()

@OptIn(InternalSerializationApi::class)
private fun TypeInfo.successTypeSerializer() = kotlinType!!.arguments[1].type!!.jvmErasure.serializer()

private fun TypeInfo.isEither() = type == Either::class