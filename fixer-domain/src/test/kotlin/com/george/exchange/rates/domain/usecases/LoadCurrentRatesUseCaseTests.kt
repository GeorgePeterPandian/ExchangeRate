package com.george.exchange.rates.domain.usecases

import com.george.exchange.rates.domain.currentRatesDM
import com.george.exchange.rates.domain.historyRatesDMs
import com.george.exchange.rates.domain.models.HistoryRatesRequestDM
import com.george.exchange.rates.domain.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadCurrentRatesUseCaseTests {

    private lateinit var usecase: LoadCurrentRatesUseCase

    private val repository: Repository = mockk()

    private val iODispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()

    private val currentRates = currentRatesDM

    private val baseCurrency = "EUR"

    private val symbols = "INR,AED"

    private val historyRatesRequestDM: HistoryRatesRequestDM =
        HistoryRatesRequestDM(baseCurrency = baseCurrency, symbols = symbols)


    @Before
    fun `begin each test with`() {
        usecase = LoadCurrentRatesUseCase(repository = repository, ioDispatcher = iODispatcher)
    }

    @Test
    fun `load all current rates data successfully`() = runTest {
        //Given
        coEvery {
            repository.loadCurrentRates()
        } returns flow { emit(currentRates) }

        coEvery {
            repository.saveCurrentRates(currentRates)
        } returns Unit

        //When
        val useCaseResults = usecase.execute().last()

        //Then
        assertEquals(currentRates, useCaseResults.success)
    }

    @Test
    fun `load all current rates data successfully and check if the base currency is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(currentRates.base, useCaseResults.success?.base)
        }

    @Test
    fun `load all current rates data successfully and check if the timestamp is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(currentRates.timestamp, useCaseResults.success?.timestamp)
        }

    @Test
    fun `load all current rates data successfully and check if the date is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(currentRates.date, useCaseResults.success?.date)
        }

    @Test
    fun `load all current rates data successfully and check if the success message is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(currentRates.success, useCaseResults.success?.success)
        }

    @Test
    fun `load all current rates data successfully and check if the rates list size is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(currentRates.rates.size, useCaseResults.success?.rates?.size)
        }

    @Test
    fun `load all current rates data successfully and check if the rates lists first rate currency is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(
                currentRates.rates.firstOrNull()?.currency,
                useCaseResults.success?.rates?.firstOrNull()?.currency
            )
        }

    @Test
    fun `load all current rates data successfully and check if the rates lists first exchange rate value is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(
                currentRates.rates.firstOrNull()?.rate,
                useCaseResults.success?.rates?.firstOrNull()?.rate
            )
        }

    @Test
    fun `load all current rates data successfully and check if the rates lists last rate currency is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(
                currentRates.rates.lastOrNull()?.currency,
                useCaseResults.success?.rates?.lastOrNull()?.currency
            )
        }

    @Test
    fun `load all current rates data successfully and check if the rates lists lat exchange rate value is as expected`() =
        runTest {
            //Given
            coEvery {
                repository.loadCurrentRates()
            } returns flow { emit(currentRates) }

            coEvery {
                repository.saveCurrentRates(currentRates)
            } returns Unit

            //When
            val useCaseResults = usecase.execute().last()

            //Then
            assertEquals(
                currentRates.rates.lastOrNull()?.rate,
                useCaseResults.success?.rates?.lastOrNull()?.rate
            )
        }

    @Test
    fun `load all data of history rates successfully`() = runTest {
        //Given
        coEvery {
            repository.loadRatesHistory(historyRatesRequestDM = historyRatesRequestDM)
        } returns flow { emit(historyRatesDMs) }

        //When
        val useCaseResults =
            repository.loadRatesHistory(historyRatesRequestDM = historyRatesRequestDM).first()

        //Then
        assertEquals(historyRatesDMs, useCaseResults)
    }

    @Test
    fun `load all data of history rates successfully and check if date is same`() = runTest {
        //Given
        coEvery {
            repository.loadRatesHistory(historyRatesRequestDM = historyRatesRequestDM)
        } returns flow { emit(historyRatesDMs) }

        //When
        val useCaseResults =
            repository.loadRatesHistory(historyRatesRequestDM = historyRatesRequestDM).first()

        //Then
        assertEquals(historyRatesDMs.first().date, useCaseResults.first().date)
    }

    @Test
    fun `load all data of history rates successfully and check if first value of map has the same currency and rate`() =
        runTest {
            //Given
            coEvery {
                repository.loadRatesHistory(historyRatesRequestDM = historyRatesRequestDM)
            } returns flow { emit(historyRatesDMs) }

            //When
            val useCaseResults =
                repository.loadRatesHistory(historyRatesRequestDM = historyRatesRequestDM).first()

            //Then
            assertEquals(
                historyRatesDMs.first().rates.keys.firstOrNull(),
                useCaseResults.first().rates.keys.firstOrNull()
            )
        }

    @Test
    fun `load all data of history rates successfully and check if last value of map has the same currency and rate`() =
        runTest {
            //Given
            coEvery {
                repository.loadRatesHistory(historyRatesRequestDM = historyRatesRequestDM)
            } returns flow { emit(historyRatesDMs) }

            //When
            val useCaseResults =
                repository.loadRatesHistory(historyRatesRequestDM = historyRatesRequestDM).first()

            //Then
            assertEquals(
                historyRatesDMs.last().rates.keys.lastOrNull(),
                useCaseResults.last().rates.keys.lastOrNull()
            )
        }
}