package com.george.exchange.rates.data.service

import com.george.exchange.rates.data.models.CurrentRatesAM
import com.george.exchange.rates.data.models.HistoryRatesAM
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface Service {

    @GET("latest")
    suspend fun loadCurrentRates(): CurrentRatesAM

    @GET("{date}")
    suspend fun loadHistoricalRates(
        @Path("date") date: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): HistoryRatesAM
}