package com.george.exchange.rates.core.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCaseWithRequestParams<in I, out O> {
    abstract fun execute(requestParams: I): Flow<O>
}