package com.george.exchange.rates.task.model

import com.george.exchange.rates.core.ui.loadCountryCode
import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesDM
import com.george.exchange.rates.domain.models.RatesDM

internal fun CurrentRatesDM.toCurrentRatesUIM() = CurrentRatesUIM(
    base = base,
    rates = rates.toRatesUIM(),
    date = date,
    success = success,
    timestamp = timestamp,
    countryCode = loadCountryCode(base)
)

@JvmName(name = "CurrentRatesRatesMapToUIM")
internal fun List<RatesDM>.toRatesUIM() = map { rateDM ->
    RateUIM(
        countryCode = loadCountryCode(rateDM.currency).uppercase(),
        currency = rateDM.currency,
        rate = rateDM.rate
    )
}

@JvmName(name = "HistoryRatesRatesMapToUIM")
internal fun List<HistoryRatesDM>.toHistoryRatesUIM() = map { historyRateDM ->
    HistoryRatesUIM(
        date = historyRateDM.date,
        rates = historyRateDM.rates.values.toList()
    )
}.sortedByDescending { it.date }