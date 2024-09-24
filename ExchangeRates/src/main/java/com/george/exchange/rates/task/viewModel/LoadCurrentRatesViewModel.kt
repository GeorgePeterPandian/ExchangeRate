package com.george.exchange.rates.task.viewModel

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.george.exchange.rates.core.domain.BusinessStates
import com.george.exchange.rates.domain.usecases.LoadCurrentRatesUseCase
import com.george.exchange.rates.task.uiStates.ExchangeRatesUIStates
import com.george.exchange.rates.task.model.RateUIM
import com.george.exchange.rates.task.model.toCurrentRatesUIM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class LoadCurrentRatesViewModel @Inject constructor(private val loadCurrentRatesUseCase: LoadCurrentRatesUseCase) :
    ViewModel() {

    private var _uiState = MutableStateFlow(ExchangeRatesUIStates())
    val uiState = _uiState.asStateFlow()

    var baseAmount by mutableStateOf("1")
    var selectedCurrencies by mutableStateOf<List<RateUIM>>(emptyList())

    init {
        loadCurrentRates()
    }

    @VisibleForTesting
    fun loadCurrentRates() {
        viewModelScope.launch {
            loadCurrentRatesUseCase.execute().collect { state ->
                when (state) {
                    is BusinessStates.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = "") }
                    }

                    is BusinessStates.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false, error = state.error.toString()
                            )
                        }
                    }

                    is BusinessStates.Success -> {
                        _uiState.update {
                            it.copy(
                                success = state.success?.toCurrentRatesUIM(),
                                isLoading = false,
                                error = ""
                            )
                        }
                    }
                }
            }
        }
    }

    fun onResetSelections() {
        selectedCurrencies = emptyList()
    }
}