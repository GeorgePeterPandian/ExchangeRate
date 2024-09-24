package com.george.exchange.rates.domain.models

data class HistoryRatesDM(
    val date: String,
    val rates: Map<String, Double>
)
