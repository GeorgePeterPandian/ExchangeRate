package com.george.exchange.rates.data.datasource.local

import com.george.exchange.rates.domain.models.CurrentRatesDM
import kotlinx.coroutines.flow.Flow

internal interface RoomDS {

    companion object {
        const val CURRENT_RATES_EXPIRATION_TIME_IN_MILLI_SEC = 60 * 60 * 1000L
    }

    suspend fun insertCurrentRates(currentRatesDM: CurrentRatesDM)

    suspend fun loadCurrentRates(): Flow<CurrentRatesDM>

    suspend fun deleteCurrentRates()

    suspend fun loadLocalStoredTimestamp(): Long?
}