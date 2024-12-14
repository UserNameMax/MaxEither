package ru.mishenko.maksim.either.networkUtils.retorfit

import retrofit2.CallAdapter
import retrofit2.Retrofit
import ru.mishenko.maksim.either.networkUtils.NetworkReasonFactory
import ru.mishenko.maksim.either.Either
import ru.mishenko.maksim.either.factory.ReasonFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class EitherCallAdapterFactory<T : Any>(
    private val reasonFactory: ReasonFactory<T>,
    private val networkReasonFactory: NetworkReasonFactory<T>
) : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): EitherCallAdapter<T, Any>? {
        if (returnType !is ParameterizedType) {
            return null
        }
        val containerType = getParameterUpperBound(0, returnType)

        if (getRawType(containerType) != Either::class.java)
            return null

        if (containerType !is ParameterizedType) {
            return null
        }

        val resultType = getParameterUpperBound(1, containerType)

        return EitherCallAdapter(
            responseType = resultType,
            reasonFactory = reasonFactory,
            networkReasonFactory = networkReasonFactory
        )
    }
}