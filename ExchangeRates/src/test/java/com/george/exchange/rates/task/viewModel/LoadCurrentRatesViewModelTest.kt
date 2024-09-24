package com.george.exchange.rates.task.viewModel

import com.george.exchange.rates.core.defaultvalues.orFalse
import com.george.exchange.rates.core.defaultvalues.orZero
import com.george.exchange.rates.core.domain.BusinessStates
import com.george.exchange.rates.core.test.CoroutineTestRule
import com.george.exchange.rates.domain.models.CurrentRatesDM
import com.george.exchange.rates.domain.repository.Repository
import com.george.exchange.rates.domain.usecases.LoadCurrentRatesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
class LoadCurrentRatesViewModelTest {

    private lateinit var viewModel: LoadCurrentRatesViewModel

    @get:Rule
    val coroutineDispatcherRule = CoroutineTestRule()

    private val repository: Repository = mockk()

    private val iODispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()

    private val useCase: LoadCurrentRatesUseCase =
        spyk(LoadCurrentRatesUseCase(repository, iODispatcher))

    private val currentRates: CurrentRatesDM = currentRatesDM

    @Before
    fun setUp() {
        viewModel = LoadCurrentRatesViewModel(useCase)
    }

    @Test
    fun `emit loading ui state when the use case emits loading business state`() = runTest {
        //Given
        val loadingBusinessState = BusinessStates.Loading<CurrentRatesDM>()
        coEvery {
            useCase.execute()
        } returns flow { emit(loadingBusinessState) }

        //When
        viewModel.loadCurrentRates()

        //Then
        assertEquals(viewModel.uiState.value.isLoading, true)
    }

    @Test
    fun `emit success ui state when the use case emits success business state and base currency is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(viewModel.uiState.value.success?.base.orEmpty(), currentRates.base)
        }

    @Test
    fun `emit success ui state when the use case emits success business state and date is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(viewModel.uiState.value.success?.date.orEmpty(), currentRates.date)
        }

    @Test
    fun `emit success ui state when the use case emits success business state and success is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(viewModel.uiState.value.success?.success.orFalse(), currentRates.success)
        }

    @Test
    fun `emit success ui state when the use case emits success business state and timestamp is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(
                viewModel.uiState.value.success?.timestamp.orZero(), currentRates.timestamp
            )
        }

    @Test
    fun `emit success ui state when the use case emits success business state and rate size is equal`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(viewModel.uiState.value.success?.rates?.size, currentRates.rates.size)
        }

    @Test
    fun `emit success ui state when the use case emits success business state and check if the rates lists first exchange rate value is as expected`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(
                viewModel.uiState.value.success?.rates?.firstOrNull()?.rate,
                currentRates.rates.firstOrNull()?.rate
            )
        }

    @Test
    fun `emit success ui state when the use case emits success business state and check if the rates lists first exchange currency value is as expected`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(
                viewModel.uiState.value.success?.rates?.firstOrNull()?.currency,
                currentRates.rates.firstOrNull()?.currency
            )
        }

    @Test
    fun `emit success ui state when the use case emits success business state and check if the rates lists last exchange rate value is as expected`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(
                viewModel.uiState.value.success?.rates?.lastOrNull()?.rate,
                currentRates.rates.lastOrNull()?.rate
            )
        }

    @Test
    fun `emit success ui state when the use case emits success business state and check if the rates lists last exchange currency value is as expected`() =
        runTest {
            //Given
            val successBusinessState = BusinessStates.Success(currentRates)
            coEvery {
                useCase.execute()
            } returns flow { emit(successBusinessState) }

            //When
            viewModel.loadCurrentRates()

            //Then
            assertEquals(
                viewModel.uiState.value.success?.rates?.lastOrNull()?.currency,
                currentRates.rates.lastOrNull()?.currency
            )
        }
}