package com.george.exchange.rates.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryRatesAM(
    @SerialName("base")
    val base: String?,
    @SerialName("date")
    val date: String?,
    @SerialName("historical")
    val historical: Boolean?,
    @SerialName("rates")
    val rates: Map<String, Double>?,
    @SerialName("success")
    val success: Boolean?,
    @SerialName("timestamp")
    val timestamp: Int?
)
