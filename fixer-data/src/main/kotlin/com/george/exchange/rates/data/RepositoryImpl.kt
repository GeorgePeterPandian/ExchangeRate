package com.george.exchange.rates.data

import com.george.exchange.rates.core.defaultvalues.orZero
import com.george.exchange.rates.core.domain.getPastFiveDates
import com.george.exchange.rates.data.datasource.local.RoomDS
import com.george.exchange.rates.data.datasource.local.RoomDS.Companion.CURRENT_RATES_EXPIRATION_TIME_IN_MILLI_SEC
import com.george.exchange.rates.data.datasource.remote.RemoteDS
import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesRequestDM
import com.george.exchange.rates.domain.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

internal class RepositoryImpl @Inject constructor(
    private val remoteDS: RemoteDS, private val localDS: RoomDS
) : Repository {

    override suspend fun loadCurrentRates(): Flow<CurrentRatesDM> {
        val localStoredTimeStamp = localDS.loadLocalStoredTimestamp()
        return when {
            localStoredTimeStamp == null || hasLocalDataExpired(localStoredTimeStamp) -> remoteDS.loadCurrentRates()
            else -> localDS.loadCurrentRates()
        }
    }

    override suspend fun saveCurrentRates(currentRatesDM: CurrentRatesDM) {
        val localStoredTimeStamp = localDS.loadLocalStoredTimestamp()
        if (hasLocalDataExpired(localStoredTimeStamp)) {
            localDS.deleteCurrentRates()
            localDS.insertCurrentRates(currentRatesDM = currentRatesDM)
        }
    }

    override suspend fun loadRatesHistory(historyRatesRequestDM: HistoryRatesRequestDM): Flow<List<HistoryRatesDM>> {
        val parentScope = CoroutineScope(coroutineContext + SupervisorJob())
        val historyList = mutableListOf<HistoryRatesDM>()
        val responses = getPastFiveDates().map { date ->
            parentScope.async {
                remoteDS.loadHistoricalRates(
                    date = date,
                    baseCurrency = historyRatesRequestDM.baseCurrency,
                    symbols = historyRatesRequestDM.symbols
                )
            }
        }.awaitAll()

        responses.merge().collect { historyRateForADate ->
            historyList.add(historyRateForADate)
        }
        return flow {
            emit(historyList)
        }
    }

    private fun hasLocalDataExpired(localStoredTimeStamp: Long?): Boolean =
        (System.currentTimeMillis() - localStoredTimeStamp.orZero() > CURRENT_RATES_EXPIRATION_TIME_IN_MILLI_SEC)
}