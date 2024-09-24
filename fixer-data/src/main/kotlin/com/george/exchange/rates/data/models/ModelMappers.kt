package com.george.exchange.rates.data.models

import com.george.exchange.rates.core.defaultvalues.orFalse
import com.george.exchange.rates.core.defaultvalues.orZero
import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesDM
import com.george.exchange.rates.domain.models.RatesDM

internal fun CurrentRatesAM.toCurrentRatesDM() = CurrentRatesDM(
    base = base.orEmpty(),
    rates = rates?.toRatesDM().orEmpty(),
    date = date.orEmpty(),
    success = success.orFalse(),
    timestamp = timestamp.orZero(),
)

@JvmName(name = "CurrentRatesRatesMapAMToDM")
internal fun Map<String, Double>.toRatesDM() = map { RatesDM(currency = it.key, rate = it.value) }

internal fun HistoryRatesAM.toHistoryRatesDM() = HistoryRatesDM(
    rates = rates.orEmpty(),
    date = date.orEmpty(),
)

internal fun CurrentRatesDM.toCurrentRatesRM() = CurrentRatesRM(
    base = base,
    rates = rates.toListRatesRM(),
    date = date,
    success = success.orFalse(),
    timestamp = timestamp.orZero() * 1000,
    storedTimestamp = System.currentTimeMillis()
)

@JvmName(name = "CurrentRatesRatesMapDMToRM")
internal fun List<RatesDM>.toListRatesRM(): List<RatesRM> =
    map { RatesRM(currency = it.currency, rate = it.rate) }

internal fun CurrentRatesRM.toCurrentRatesDM() = CurrentRatesDM(
    id = id,
    base = base,
    rates = rates.toListRatesDM(),
    date = date,
    success = success.orFalse(),
    timestamp = timestamp.orZero(),
    storedTimestamp = storedTimestamp.orZero()
)

@JvmName(name = "CurrentRatesRatesMapRMToDM")
internal fun List<RatesRM>.toListRatesDM(): List<RatesDM> =
    map { RatesDM(currency = it.currency, rate = it.rate) }

