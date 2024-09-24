package com.george.exchange.rates.core.domain

sealed class BusinessStates<T>(val success: T? = null, val error: ErrorEntities? = null) {
    class Loading<T> : BusinessStates<T>()

    class Success<T>(data: T) : BusinessStates<T>(success = data)

    class Error<T>(error: ErrorEntities) : BusinessStates<T>(error = error)
}
