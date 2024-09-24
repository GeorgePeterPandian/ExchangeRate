package com.george.exchange.rates.task.model

data class CurrentRatesUIM(
    val base: String,
    val countryCode: String,
    val date: String,
    val rates: List<RateUIM>,
    val success: Boolean,
    val timestamp: Long
)