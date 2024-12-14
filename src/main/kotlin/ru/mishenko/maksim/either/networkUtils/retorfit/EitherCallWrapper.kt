package ru.mishenko.maksim.either.networkUtils.retorfit

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mishenko.maksim.either.*
import ru.mishenko.maksim.either.handling.mapHandling
import ru.mishenko.maksim.either.networkUtils.NetworkReasonFactory
import ru.mishenko.maksim.either.networkUtils.retorfit.CallUtils.enqueue
import ru.mishenko.maksim.either.factory.ReasonFactory
import ru.mishenko.maksim.either.factory.either
import ru.mishenko.maksim.either.handling.recover

class EitherCallWrapper<E, T>(
    private val call: Call<T>,
    private val reasonFactory: ReasonFactory<E>,
    private val networkReasonFactory: NetworkReasonFactory<E>
) : Call<Either<E, T>> {

    private fun <T> executeResponse(call: () -> Response<T>) = either(reasonFactory) {
        val response: Response<T> = call()
        if (response.isSuccessful) {
            Either.Success<T>(response.body()!!)
        } else {
            val body = response.errorBody()?.string() ?: ""
            val code = response.code()
            Either.Failure(networkReasonFactory(code, body))
        }
    }.flatten().run { Response.success(this) }

    override fun execute(): Response<Either<E, T>> {
        return executeResponse { call.execute() }
    }

    override fun enqueue(callback: Callback<Either<E, T>>) {
        call.enqueue(reasonFactory) { call, response ->
            response
                .map { executeResponse { it } }
                .recover(reasonFactory) { Response.success(Either.Failure(it)) }
                .onSuccess { callback.onResponse(this, it) }
        }
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() {
        call.cancel()
    }

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

    override fun clone(): Call<Either<E, T>> = EitherCallWrapper(
        call = call.clone(),
        reasonFactory = reasonFactory,
        networkReasonFactory = networkReasonFactory
    )
}