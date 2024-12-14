package ru.mishenko.maksim.either.networkUtils.retorfit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.factory.ReasonFactory
import ru.mishenko.maksim.either.networkUtils.NetworkReasonFactory

object CallUtils {
    fun <T> callback(
        onSuccess: (Call<T>, Response<T>) -> Unit,
        onFail: (Call<T>, Throwable) -> Unit
    ) = object : Callback<T> {
        override fun onResponse(p0: Call<T>, p1: Response<T>) {
            onSuccess(p0, p1)
        }

        override fun onFailure(p0: Call<T>, p1: Throwable) {
            onFail(p0, p1)
        }
    }

    fun <T> Call<T>.enqueue(
        onSuccess: (Call<T>, Response<T>) -> Unit,
        onFail: (Call<T>, Throwable) -> Unit
    ) {
        enqueue(callback(onSuccess, onFail))
    }

    fun <E, T> Call<T>.enqueue(
        reasonFactory: ReasonFactory<E>,
        callback: (Call<T>, Either<E, Response<T>>) -> Unit
    ) {
        enqueue(
            onSuccess = { call, response -> callback(call, Either.Success(response)) },
            onFail = { call, e -> callback(call, Either.Failure(reasonFactory(e))) }
        )
    }
}