package ru.mishenko.maksim.either.networkUtils.retorfit

import retrofit2.Call
import retrofit2.CallAdapter
import ru.mishenko.maksim.either.networkUtils.NetworkReasonFactory
import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.factory.ReasonFactory
import java.lang.reflect.Type

class EitherCallAdapter<E, T>(
    private val responseType: Type,
    private val reasonFactory: ReasonFactory<E>,
    private val networkReasonFactory: NetworkReasonFactory<E>
) : CallAdapter<T, Call<Either<E, T>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Call<Either<E, T>> = EitherCallWrapper(
        call = call,
        reasonFactory = reasonFactory,
        networkReasonFactory = networkReasonFactory
    )
}