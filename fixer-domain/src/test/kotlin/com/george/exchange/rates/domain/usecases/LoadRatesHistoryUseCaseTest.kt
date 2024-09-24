package com.george.exchange.rates.domain.usecases

import com.george.exchange.rates.domain.historyRatesDMs
import com.george.exchange.rates.domain.models.HistoryRatesRequestDM
import com.george.exchange.rates.domain.models.HistoryRatesRequestParams
import com.george.exchange.rates.domain.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadRatesHistoryUseCaseTest {

    private val repository: Repository = mockk()

    private val iODispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()

    private lateinit var usecase: LoadRatesHistoryUseCase

    private val repositoryResults = historyRatesDMs

    private val requestDM = HistoryRatesRequestDM(
        baseCurrency = "EUR", symbols = "INR,AED"
    )

    private val requestParams = HistoryRatesRequestParams(baseCurrency = "EUR", symbols = "INR,AED")

    @Before
    fun `begin each test with`() {
        usecase = LoadRatesHistoryUseCase(repository = repository, ioDispatcher = iODispatcher)
    }

    @Test
    fun `load history rates data when success and check if size is same`() = runTest {
        //Given
        coEvery { repository.loadRatesHistory(historyRatesRequestDM = requestDM) } returns flow {
            emit(repositoryResults)
        }

        //When
        val result = usecase.execute(requestParams = requestParams).last()


        //Then
        assertEquals(repositoryResults.size, result.success?.size)
    }

    @Test
    fun `load history rates data when success and check if first value date is same`() = runTest {
        //Given
        coEvery { repository.loadRatesHistory(historyRatesRequestDM = requestDM) } returns flow {
            emit(repositoryResults)
        }

        //When
        val result = usecase.execute(requestParams = requestParams).last()


        //Then
        assertEquals(repositoryResults.firstOrNull()?.date, result.success?.firstOrNull()?.date)
    }

    @Test
    fun `load history rates data when success and check if last value date is same`() = runTest {
        //Given
        coEvery { repository.loadRatesHistory(historyRatesRequestDM = requestDM) } returns flow {
            emit(repositoryResults)
        }

        //When
        val result = usecase.execute(requestParams = requestParams).last()


        //Then
        assertEquals(repositoryResults.lastOrNull()?.date, result.success?.lastOrNull()?.date)
    }

    @Test
    fun `load history rates data when success and check if first value of the currency is same`() =
        runTest {
            //Given
            coEvery { repository.loadRatesHistory(historyRatesRequestDM = requestDM) } returns flow {
                emit(repositoryResults)
            }

            //When
            val result = usecase.execute(requestParams = requestParams).last()


            //Then
            assertEquals(
                repositoryResults.firstOrNull()?.rates?.keys?.firstOrNull(),
                result.success?.firstOrNull()?.rates?.keys?.firstOrNull()
            )
        }

    @Test
    fun `load history rates data when success and check if last value of the currency is same`() =
        runTest {
            //Given
            coEvery { repository.loadRatesHistory(historyRatesRequestDM = requestDM) } returns flow {
                emit(repositoryResults)
            }

            //When
            val result = usecase.execute(requestParams = requestParams).last()


            //Then
            assertEquals(
                repositoryResults.lastOrNull()?.rates?.keys?.lastOrNull(),
                result.success?.lastOrNull()?.rates?.keys?.lastOrNull()
            )
        }

    @Test
    fun `load history rates data when success and check if first value of the rate is same`() =
        runTest {
            //Given
            coEvery { repository.loadRatesHistory(historyRatesRequestDM = requestDM) } returns flow {
                emit(repositoryResults)
            }

            //When
            val result = usecase.execute(requestParams = requestParams).last()


            //Then
            assertEquals(
                repositoryResults.firstOrNull()?.rates?.values?.firstOrNull(),
                result.success?.firstOrNull()?.rates?.values?.firstOrNull()
            )
        }

    @Test
    fun `load history rates data when success and check if last value of the rate is same`() =
        runTest {
            //Given
            coEvery { repository.loadRatesHistory(historyRatesRequestDM = requestDM) } returns flow {
                emit(repositoryResults)
            }

            //When
            val result = usecase.execute(requestParams = requestParams).last()


            //Then
            assertEquals(
                repositoryResults.lastOrNull()?.rates?.values?.lastOrNull(),
                result.success?.lastOrNull()?.rates?.values?.lastOrNull()
            )
        }
}