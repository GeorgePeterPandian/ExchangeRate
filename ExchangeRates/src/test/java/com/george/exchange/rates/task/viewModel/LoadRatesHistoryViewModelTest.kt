package com.george.exchange.rates.task.viewModel

import com.george.exchange.rates.core.domain.BusinessStates
import com.george.exchange.rates.core.test.CoroutineTestRule
import com.george.exchange.rates.domain.models.HistoryRatesDM
import com.george.exchange.rates.domain.models.HistoryRatesRequestParams
import com.george.exchange.rates.domain.repository.Repository
import com.george.exchange.rates.domain.usecases.LoadRatesHistoryUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
class LoadRatesHistoryViewModelTest {

    private lateinit var viewModel: LoadRatesHistoryViewModel

    @get:Rule
    val coroutineDispatcherRule = CoroutineTestRule()

    private val repository: Repository = mockk()

    private val iODispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()

    private val useCase: LoadRatesHistoryUseCase =
        spyk(LoadRatesHistoryUseCase(repository, iODispatcher))

    private val historyRates = historyRatesDMs

    private val requestParams = HistoryRatesRequestParams(baseCurrency = "EUR", symbols = "INR,AED")

    @Before
    fun setUp() {
        viewModel = LoadRatesHistoryViewModel(useCase)
    }

    @Test
    fun `emit loading ui state when the use case emits loading business state`() = runTest {
        //Given
        val loadingBusinessState = BusinessStates.Loading<List<HistoryRatesDM>>()
        coEvery {
            useCase.execute(requestParams = requestParams)
        } returns flow { emit(loadingBusinessState) }

        //When
        viewModel.loadRatesHistory(baseCurrency = "EUR", symbols = "INR,AED")

        //Then
        assertEquals(viewModel.uiState.value.isLoading, true)
    }

    @Test
    fun `emit success ui state when the use case emits success business state and check if size is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(historyRates)
            coEvery {
                useCase.execute(requestParams = requestParams)
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadRatesHistory(baseCurrency = "EUR", symbols = "INR,AED")

            //Then
            assertEquals(viewModel.uiState.value.success?.size, historyRates.size)
        }

    @Test
    fun `emit success ui state when the use case emits success business state and check if first date is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(historyRates)
            coEvery {
                useCase.execute(requestParams = requestParams)
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadRatesHistory(baseCurrency = "EUR", symbols = "INR,AED")

            //Then
            assertEquals(
                viewModel.uiState.value.success?.firstOrNull()?.date,
                historyRates.firstOrNull()?.date
            )
        }

    @Test
    fun `emit success ui state when the use case emits success business state and check if last date is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(historyRates)
            coEvery {
                useCase.execute(requestParams = requestParams)
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadRatesHistory(baseCurrency = "EUR", symbols = "INR,AED")

            //Then
            assertEquals(
                viewModel.uiState.value.success?.lastOrNull()?.date, historyRates.lastOrNull()?.date
            )
        }

    @Test
    fun `emit success ui state when the use case emits success business state and check if first rate is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(historyRates)
            coEvery {
                useCase.execute(requestParams = requestParams)
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadRatesHistory(baseCurrency = "EUR", symbols = "INR,AED")

            //Then
            assertEquals(
                viewModel.uiState.value.success?.firstOrNull()?.rates?.firstOrNull(),
                historyRates.firstOrNull()?.rates?.values?.firstOrNull()
            )
        }

    @Test
    fun `emit success ui state when the use case emits success business state and check if last rate is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(historyRates)
            coEvery {
                useCase.execute(requestParams = requestParams)
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadRatesHistory(baseCurrency = "EUR", symbols = "INR,AED")

            //Then
            assertEquals(
                viewModel.uiState.value.success?.lastOrNull()?.rates?.lastOrNull(),
                historyRates.lastOrNull()?.rates?.values?.lastOrNull()
            )
        }
}