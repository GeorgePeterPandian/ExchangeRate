package com.george.exchange.rates.data.datasource.remote

import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesDM
import kotlinx.coroutines.flow.Flow

internal interface RemoteDS {

    suspend fun loadCurrentRates(): Flow<CurrentRatesDM>

    suspend fun loadHistoricalRates(
        date: String, baseCurrency: String, symbols: String
    ): Flow<HistoryRatesDM>
}