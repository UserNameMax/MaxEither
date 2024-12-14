package ru.mishenko.maksim.either.networkUtils.retorfit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
}