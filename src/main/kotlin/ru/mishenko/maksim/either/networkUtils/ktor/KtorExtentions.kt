package ru.mishenko.maksim.either.networkUtils.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.factory.ReasonFactory
import ru.mishenko.maksim.either.factory.either

object KtorExtentions {
    suspend inline fun <reified T> HttpClient.getCatching(
        reasonFactory: ReasonFactory<T>,
        urlString: String,
        builder: HttpRequestBuilder.() -> Unit = {}
    ) = either(reasonFactory) { get(urlString, builder) }

    suspend inline fun <reified T> HttpClient.postCatching(
        reasonFactory: ReasonFactory<T>,
        urlString: String,
        builder: HttpRequestBuilder.() -> Unit = {}
    ) = either(reasonFactory) { post(urlString, builder) }

    suspend inline fun <reified T> HttpClient.putCatching(
        reasonFactory: ReasonFactory<T>,
        urlString: String,
        builder: HttpRequestBuilder.() -> Unit = {}
    ) = either(reasonFactory) { put(urlString, builder) }

    suspend inline fun <reified T> HttpClient.deleteCatching(
        reasonFactory: ReasonFactory<T>,
        urlString: String,
        builder: HttpRequestBuilder.() -> Unit = {}
    ) = either(reasonFactory) { delete(urlString, builder) }

    suspend inline fun <reified T> HttpClient.requestCatching(
        reasonFactory: ReasonFactory<T>,
        urlString: String,
        builder: HttpRequestBuilder.() -> Unit = {}
    ) = either(reasonFactory) { request(urlString, builder) }

    suspend inline fun <reified T,reified E> Either<E, HttpResponse>.body(): Either<E, T> = when (this) {
        is Either.Failure -> this
        is Either.Success -> data.body<Either<E, T>>()
    }
}