package com.george.exchange.rates.core.domain

sealed class ErrorEntities {
    object NetworkError : ErrorEntities()

    object UnknownError : ErrorEntities()
}
