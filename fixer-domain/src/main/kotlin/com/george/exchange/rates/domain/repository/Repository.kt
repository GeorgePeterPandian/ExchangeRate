package com.george.exchange.rates.domain.repository

import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesRequestDM
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun loadCurrentRates(): Flow<CurrentRatesDM>

    suspend fun saveCurrentRates(currentRatesDM: CurrentRatesDM)

    suspend fun loadRatesHistory(historyRatesRequestDM: HistoryRatesRequestDM): Flow<List<HistoryRatesDM>>
}