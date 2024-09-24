package com.george.exchange.rates.data.datasource.remote

import com.george.exchange.rates.data.service.ApiService
import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesDM
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RemoteDSImpl @Inject constructor(private val apiService: ApiService) : RemoteDS {

    override suspend fun loadCurrentRates(): Flow<CurrentRatesDM> = apiService.loadCurrentRates()

    override suspend fun loadHistoricalRates(
        date: String, baseCurrency: String, symbols: String
    ): Flow<HistoryRatesDM> = apiService.loadHistoricalRates(
        date = date, baseCurrency = baseCurrency, symbols = symbols
    )
}