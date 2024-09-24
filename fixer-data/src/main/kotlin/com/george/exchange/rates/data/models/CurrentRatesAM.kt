package com.george.exchange.rates.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CurrentRatesAM(
    val base: String?,
    val date: String?,
    val rates: Map<String, Double>?,
    val success: Boolean?,
    val timestamp: Long?
)