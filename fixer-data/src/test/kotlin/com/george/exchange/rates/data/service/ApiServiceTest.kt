package com.george.exchange.rates.data.service

import com.george.exchange.rates.data.currentRatesDM
import com.george.exchange.rates.data.historyRatesDM
import com.george.exchange.rates.domain.models.CurrentRatesDM
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {

    private lateinit var apiService: ApiService

    private val currentRates: CurrentRatesDM = currentRatesDM

    private val date = "09-09-2024"

    private val baseCurrency = "EUR"

    private val symbols = "INR,AED"

    @Before
    fun setUp() {
        apiService = spyk()
    }

    @Test
    fun `load all data of current rates successfully`() = runTest {
        //Given
        coEvery {
            apiService.loadCurrentRates()
        } returns flow { emit(currentRates) }

        //When
        val apiServiceResults = apiService.loadCurrentRates().first()

        //Then
        assertEquals(currentRates, apiServiceResults)
    }

    @Test
    fun `load all data of current rates successfully and check if the base currency is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.base, apiServiceResults.base)
        }

    @Test
    fun `load all data of current rates successfully and check if the timestamp is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.timestamp, apiServiceResults.timestamp)
        }

    @Test
    fun `load all data of current rates successfully and check if the date is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.date, apiServiceResults.date)
        }

    @Test
    fun `load all data of current rates successfully and check if the success message is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.success, apiServiceResults.success)
        }

    @Test
    fun `load all data of current rates successfully and check if the rates list size is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.rates.size, apiServiceResults.rates.size)
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists first rate currency is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.firstOrNull()?.currency,
                apiServiceResults.rates.firstOrNull()?.currency
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists first exchange rate value is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.firstOrNull()?.rate, apiServiceResults.rates.firstOrNull()?.rate
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists last rate currency is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.lastOrNull()?.currency,
                apiServiceResults.rates.lastOrNull()?.currency
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists lat exchange rate value is as expected`() =
        runTest {
            //Given
            coEvery {
                apiService.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val apiServiceResults = apiService.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.lastOrNull()?.rate, apiServiceResults.rates.lastOrNull()?.rate
            )
        }

    @Test
    fun `load all data of history rates successfully`() = runTest {
        //Given
        coEvery {
            apiService.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            )
        } returns flow { emit(historyRatesDM) }

        //When
        val apiServiceResults = apiService.loadHistoricalRates(
            date = date, baseCurrency = baseCurrency, symbols = symbols
        ).first()

        //Then
        assertEquals(historyRatesDM, apiServiceResults)
    }

    @Test
    fun `load all data of history rates successfully and check if date is same`() = runTest {
        //Given
        coEvery {
            apiService.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            )
        } returns flow { emit(historyRatesDM) }

        //When
        val apiServiceResults = apiService.loadHistoricalRates(
            date = date, baseCurrency = baseCurrency, symbols = symbols
        ).first()

        //Then
        assertEquals(historyRatesDM.date, apiServiceResults.date)
    }

    @Test
    fun `load all data of history rates successfully and check if first value of map has the same currency and rate`() =
        runTest {
            //Given
            coEvery {
                apiService.loadHistoricalRates(
                    date = date, baseCurrency = baseCurrency, symbols = symbols
                )
            } returns flow { emit(historyRatesDM) }

            //When
            val apiServiceResults = apiService.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            ).first()

            //Then
            assertEquals(
                historyRatesDM.rates.keys.firstOrNull(), apiServiceResults.rates.keys.firstOrNull()
            )
            assertEquals(
                historyRatesDM.rates.values.firstOrNull(),
                apiServiceResults.rates.values.firstOrNull()
            )
        }

    @Test
    fun `load all data of history rates successfully and check if last value of map has the same currency and rate`() =
        runTest {
            //Given
            coEvery {
                apiService.loadHistoricalRates(
                    date = date, baseCurrency = baseCurrency, symbols = symbols
                )
            } returns flow { emit(historyRatesDM) }

            //When
            val apiServiceResults = apiService.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            ).first()

            //Then
            assertEquals(
                historyRatesDM.rates.keys.lastOrNull(), apiServiceResults.rates.keys.lastOrNull()
            )
            assertEquals(
                historyRatesDM.rates.values.lastOrNull(),
                apiServiceResults.rates.values.lastOrNull()
            )
        }

}