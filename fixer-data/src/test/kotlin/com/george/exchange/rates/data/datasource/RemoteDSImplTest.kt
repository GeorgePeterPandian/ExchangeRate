package com.george.exchange.rates.data.datasource

import com.george.exchange.rates.data.currentRatesDM
import com.george.exchange.rates.data.datasource.remote.RemoteDS
import com.george.exchange.rates.data.datasource.remote.RemoteDSImpl
import com.george.exchange.rates.data.historyRatesDM
import com.george.exchange.rates.data.service.ApiService
import com.george.exchange.rates.domain.models.CurrentRatesDM
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDSImplTest {

    private lateinit var remoteDS: RemoteDS

    private val service: ApiService = spyk()

    private val currentRates: CurrentRatesDM = currentRatesDM

    private val date = "09-09-2024"

    private val baseCurrency = "EUR"

    private val symbols = "INR,AED"

    @Before
    fun setUp() {
        remoteDS = RemoteDSImpl(apiService = service)
    }

    @Test
    fun `load all data of current rates successfully`() = runTest {
        //Given
        coEvery {
            service.loadCurrentRates()
        } returns flow { emit(currentRates) }

        //When
        val remoteResults = remoteDS.loadCurrentRates().first()

        //Then
        assertEquals(currentRates, remoteResults)
    }

    @Test
    fun `load all data of current rates successfully and check if the base currency is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.base, remoteResults.base)
        }

    @Test
    fun `load all data of current rates successfully and check if the timestamp is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.timestamp, remoteResults.timestamp)
        }

    @Test
    fun `load all data of current rates successfully and check if the date is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.date, remoteResults.date)
        }

    @Test
    fun `load all data of current rates successfully and check if the success message is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.success, remoteResults.success)
        }

    @Test
    fun `load all data of current rates successfully and check if the rates list size is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.rates.size, remoteResults.rates.size)
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists first rate currency is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.firstOrNull()?.currency,
                remoteResults.rates.firstOrNull()?.currency
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists first exchange rate value is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.firstOrNull()?.rate, remoteResults.rates.firstOrNull()?.rate
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists last rate currency is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.lastOrNull()?.currency,
                remoteResults.rates.lastOrNull()?.currency
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists lat exchange rate value is as expected`() =
        runTest {
            //Given
            coEvery {
                service.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val remoteResults = remoteDS.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.lastOrNull()?.rate, remoteResults.rates.lastOrNull()?.rate
            )
        }

    @Test
    fun `load all data of history rates successfully`() = runTest {
        //Given
        coEvery {
            service.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            )
        } returns flow { emit(historyRatesDM) }

        //When
        val remoteResults =
            service.loadHistoricalRates(date = date, baseCurrency = baseCurrency, symbols = symbols)
                .first()

        //Then
        assertEquals(historyRatesDM, remoteResults)
    }

    @Test
    fun `load all data of history rates successfully and check if date is same`() = runTest {
        //Given
        coEvery {
            service.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            )
        } returns flow { emit(historyRatesDM) }

        //When
        val remoteResults =
            service.loadHistoricalRates(date = date, baseCurrency = baseCurrency, symbols = symbols)
                .first()

        //Then
        assertEquals(historyRatesDM.date, remoteResults.date)
    }

    @Test
    fun `load all data of history rates successfully and check if first value of map has the same currency and rate`() =
        runTest {
            //Given
            coEvery {
                service.loadHistoricalRates(
                    date = date, baseCurrency = baseCurrency, symbols = symbols
                )
            } returns flow { emit(historyRatesDM) }

            //When
            val remoteResults = service.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            ).first()

            //Then
            assertEquals(
                historyRatesDM.rates.keys.firstOrNull(), remoteResults.rates.keys.firstOrNull()
            )
            assertEquals(
                historyRatesDM.rates.values.firstOrNull(), remoteResults.rates.values.firstOrNull()
            )
        }

    @Test
    fun `load all data of history rates successfully and check if last value of map has the same currency and rate`() =
        runTest {
            //Given
            coEvery {
                service.loadHistoricalRates(
                    date = date, baseCurrency = baseCurrency, symbols = symbols
                )
            } returns flow { emit(historyRatesDM) }

            //When
            val remoteResults = service.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            ).first()

            //Then
            assertEquals(
                historyRatesDM.rates.keys.lastOrNull(), remoteResults.rates.keys.lastOrNull()
            )
            assertEquals(
                historyRatesDM.rates.values.lastOrNull(), remoteResults.rates.values.lastOrNull()
            )
        }

}