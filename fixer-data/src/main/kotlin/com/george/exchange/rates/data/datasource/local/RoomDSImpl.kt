package com.george.exchange.rates.data.datasource.local

import com.george.exchange.rates.data.models.toCurrentRatesDM
import com.george.exchange.rates.data.models.toCurrentRatesRM
import com.george.exchange.rates.domain.models.CurrentRatesDM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RoomDSImpl @Inject constructor(private val dao: FixerDao) : RoomDS {

    override suspend fun insertCurrentRates(currentRatesDM: CurrentRatesDM) =
        dao.insertCurrentRates(currentRatesDM.toCurrentRatesRM())

    override suspend fun loadCurrentRates(): Flow<CurrentRatesDM> =
        dao.loadCurrentRates().map { currentRatesRM -> currentRatesRM.toCurrentRatesDM() }

    override suspend fun deleteCurrentRates() = dao.deleteCurrentRates()

    override suspend fun loadLocalStoredTimestamp(): Long? = dao.loadLocalStoredTimestamp()
}