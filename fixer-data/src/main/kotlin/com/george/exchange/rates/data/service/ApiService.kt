package com.george.exchange.rates.data.service

import com.george.exchange.rates.data.BuildConfig
import com.george.exchange.rates.data.models.toCurrentRatesDM
import com.george.exchange.rates.data.models.toHistoryRatesDM
import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesDM
import com.george.exchange.rates.network.provideRetrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

internal class ApiService {

    private val retrofit by lazy {
        provideRetrofit(BuildConfig.REST_API, BuildConfig.DEBUG).newBuilder().addConverterFactory(
            Json.asConverterFactory(
                "application/json; charset=UTF8".toMediaType()
            )
        ).build()
    }

    private val service by lazy {
        retrofit.create(Service::class.java)
    }

    suspend fun loadCurrentRates(): Flow<CurrentRatesDM> = flow {
        emit(service.loadCurrentRates().toCurrentRatesDM())
    }

    suspend fun loadHistoricalRates(
        date: String, baseCurrency: String, symbols: String
    ): Flow<HistoryRatesDM> = flow {
        emit(
            service.loadHistoricalRates(date = date, base = baseCurrency, symbols = symbols)
                .toHistoryRatesDM()
        )
    }
}