package com.george.exchange.rates.data

import com.george.exchange.rates.data.models.CurrentRatesRM
import com.george.exchange.rates.data.models.RatesRM

private val aedRatesRM = RatesRM(currency = "AED", rate = 4.074252)
private val afnRatesRM = RatesRM(currency = "AFN", rate = 78.757676)
private val allRatesRM = RatesRM(currency = "ALL", rate = 99.345531)
private val amdRatesRM = RatesRM(currency = "AMD", rate = 429.382853)


internal val currentRatesRM: CurrentRatesRM = CurrentRatesRM(
    id = 1L,
    base = "EUR",
    date = "2024-09-07",
    rates = listOf(aedRatesRM, afnRatesRM, allRatesRM, amdRatesRM),
    success = true,
    timestamp = 1725679143,
    storedTimestamp = 1725679147
)