package com.george.exchange.rates.domain.usecases

import com.george.exchange.rates.core.dispatchers.IoDispatcher
import com.george.exchange.rates.core.domain.BusinessStates
import com.george.exchange.rates.core.domain.ErrorEntities
import com.george.exchange.rates.core.domain.FlowUseCaseWithRequestParams
import com.george.exchange.rates.domain.models.HistoryRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesRequestDM
import com.george.exchange.rates.domain.models.HistoryRatesRequestParams
import com.george.exchange.rates.domain.repository.Repository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class LoadRatesHistoryUseCase @Inject constructor(
    private val repository: Repository, @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCaseWithRequestParams<HistoryRatesRequestParams, BusinessStates<List<HistoryRatesDM>>>() {

    override fun execute(requestParams: HistoryRatesRequestParams): Flow<BusinessStates<List<HistoryRatesDM>>> =
        flow {
            emit(BusinessStates.Loading())
            try {
                repository.loadRatesHistory(
                    HistoryRatesRequestDM(
                        baseCurrency = requestParams.baseCurrency, symbols = requestParams.symbols
                    )
                ).collect { emit(BusinessStates.Success(it)) }
            } catch (exception: Exception) {
                when (exception) {
                    is IOException -> emit(BusinessStates.Error(ErrorEntities.NetworkError))
                    is CancellationException -> throw exception
                    else -> emit(BusinessStates.Error(ErrorEntities.UnknownError))
                }
            }
        }.flowOn(ioDispatcher)
}