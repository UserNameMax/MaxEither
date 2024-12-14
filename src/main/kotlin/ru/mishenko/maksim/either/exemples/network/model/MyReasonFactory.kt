package ru.mishenko.maksim.either.exemples.network.model

import ru.mishenko.maksim.either.networkUtils.NetworkReasonFactory
import ru.mishenko.maksim.either.factory.ReasonFactory

object MyReasonFactory : ReasonFactory<MyReason>, NetworkReasonFactory<MyReason> {
    override fun invoke(e: Throwable): MyReason = MyReason.TextReason(e.stackTraceToString())
    override fun invoke(code: Int, data: String): MyReason = MyReason.NetworkReason(code, data)
}