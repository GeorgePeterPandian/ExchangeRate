package com.george.exchange.rates.data

import com.george.exchange.rates.data.datasource.local.RoomDS
import com.george.exchange.rates.data.datasource.remote.RemoteDS
import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryImplTest {

    private lateinit var repository: Repository

    private val remoteDS: RemoteDS = mockk()

    private val localDS: RoomDS = mockk()

    private val currentRates: CurrentRatesDM = currentRatesDM

    private val date = "09-09-2024"

    private val baseCurrency = "EUR"

    private val symbols = "INR,AED"


    @Before
    fun setUp() {
        repository = RepositoryImpl(remoteDS = remoteDS, localDS = localDS)
    }

    @Test
    fun `load all data of current rates successfully`() = runTest {
        //Given
        coEvery {
            localDS.loadLocalStoredTimestamp()
        } returns currentRates.storedTimestamp

        coEvery {
            remoteDS.loadCurrentRates()
        } returns flow { emit(currentRates) }

        //When
        val repositoryResults = repository.loadCurrentRates().first()

        //Then
        assertEquals(currentRates, repositoryResults)
    }

    @Test
    fun `load all data of current rates successfully and check if the base currency is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.base, repositoryResults.base)
        }

    @Test
    fun `load all data of current rates successfully and check if the timestamp is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.timestamp, repositoryResults.timestamp)
        }

    @Test
    fun `load all data of current rates successfully and check if the date is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.date, repositoryResults.date)
        }

    @Test
    fun `load all data of current rates successfully and check if the success message is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.success, repositoryResults.success)
        }

    @Test
    fun `load all data of current rates successfully and check if the rates list size is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(currentRates.rates.size, repositoryResults.rates.size)
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists first rate currency is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.firstOrNull()?.currency,
                repositoryResults.rates.firstOrNull()?.currency
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists first exchange rate value is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.firstOrNull()?.rate, repositoryResults.rates.firstOrNull()?.rate
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists last rate currency is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.lastOrNull()?.currency,
                repositoryResults.rates.lastOrNull()?.currency
            )
        }

    @Test
    fun `load all data of current rates successfully and check if the rates lists lat exchange rate value is as expected`() =
        runTest {
            //Given
            coEvery {
                localDS.loadLocalStoredTimestamp()
            } returns currentRates.storedTimestamp

            coEvery {
                remoteDS.loadCurrentRates()
            } returns flow { emit(currentRates) }

            //When
            val repositoryResults = repository.loadCurrentRates().first()

            //Then
            assertEquals(
                currentRates.rates.lastOrNull()?.rate, repositoryResults.rates.lastOrNull()?.rate
            )
        }


    @Test
    fun `load all data of history rates successfully`() = runTest {
        //Given
        coEvery {
            remoteDS.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            )
        } returns flow { emit(historyRatesDM) }

        //When
        val repositoryResults = remoteDS.loadHistoricalRates(
            date = date, baseCurrency = baseCurrency, symbols = symbols
        ).first()

        //Then
        assertEquals(historyRatesDM, repositoryResults)
    }

    @Test
    fun `load all data of history rates successfully and check if date is same`() = runTest {
        //Given
        coEvery {
            remoteDS.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            )
        } returns flow { emit(historyRatesDM) }

        //When
        val repositoryResults = remoteDS.loadHistoricalRates(
            date = date, baseCurrency = baseCurrency, symbols = symbols
        ).first()

        //Then
        assertEquals(historyRatesDM.date, repositoryResults.date)
    }

    @Test
    fun `load all data of history rates successfully and check if first value of map has the same currency and rate`() =
        runTest {
            //Given
            coEvery {
                remoteDS.loadHistoricalRates(
                    date = date, baseCurrency = baseCurrency, symbols = symbols
                )
            } returns flow { emit(historyRatesDM) }

            //When
            val repositoryResults = remoteDS.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            ).first()

            //Then
            assertEquals(
                historyRatesDM.rates.keys.firstOrNull(), repositoryResults.rates.keys.firstOrNull()
            )
            assertEquals(
                historyRatesDM.rates.values.firstOrNull(),
                repositoryResults.rates.values.firstOrNull()
            )
        }

    @Test
    fun `load all data of history rates successfully and check if last value of map has the same currency and rate`() =
        runTest {
            //Given
            coEvery {
                remoteDS.loadHistoricalRates(
                    date = date, baseCurrency = baseCurrency, symbols = symbols
                )
            } returns flow { emit(historyRatesDM) }

            //When
            val repositoryResults = remoteDS.loadHistoricalRates(
                date = date, baseCurrency = baseCurrency, symbols = symbols
            ).first()

            //Then
            assertEquals(
                historyRatesDM.rates.keys.lastOrNull(), repositoryResults.rates.keys.lastOrNull()
            )
            assertEquals(
                historyRatesDM.rates.values.lastOrNull(),
                repositoryResults.rates.values.lastOrNull()
            )
        }

}