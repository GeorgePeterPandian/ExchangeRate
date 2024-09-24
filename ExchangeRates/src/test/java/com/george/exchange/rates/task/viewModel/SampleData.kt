package com.george.exchange.rates.task.viewModel

import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesDM
import com.george.exchange.rates.domain.models.RatesDM

private val aedRatesDM = RatesDM(currency = "AED", rate = 4.074252)
private val afnRatesDM = RatesDM(currency = "AFN", rate = 78.757676)
private val allRatesDM = RatesDM(currency = "ALL", rate = 99.345531)
private val amdRatesDM = RatesDM(currency = "AMD", rate = 429.382853)


internal val currentRatesDM: CurrentRatesDM = CurrentRatesDM(
    base = "EUR",
    date = "2024-09-07",
    rates = listOf(aedRatesDM, afnRatesDM, allRatesDM, amdRatesDM),
    success = true,
    timestamp = 1725679143,
)

internal val historyRatesDM: HistoryRatesDM = HistoryRatesDM(
    date = "09-09-2024", rates = mapOf(Pair("INR", 1.0), Pair("AED", 1.0))
)

internal val historyRatesDMOther: HistoryRatesDM = HistoryRatesDM(
    date = "09-09-2024", rates = mapOf(Pair("GBP", 1.0), Pair("EGY", 1.0))
)

internal val historyRatesDMs = listOf(historyRatesDM, historyRatesDMOther)

