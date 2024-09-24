package com.george.exchange.rates.domain.models

data class CurrentRatesDM(
    val id: Long = 0L,
    val base: String,
    val date: String,
    val rates: List<RatesDM>,
    val success: Boolean,
    val timestamp: Long,
    val storedTimestamp: Long = 0L
)
