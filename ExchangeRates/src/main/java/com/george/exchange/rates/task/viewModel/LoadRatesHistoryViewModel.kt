package com.george.exchange.rates.task.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.george.exchange.rates.core.domain.BusinessStates
import com.george.exchange.rates.domain.models.HistoryRatesRequestParams
import com.george.exchange.rates.domain.usecases.LoadRatesHistoryUseCase
import com.george.exchange.rates.task.model.toHistoryRatesUIM
import com.george.exchange.rates.task.uiStates.HistoryRatesUIStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class LoadRatesHistoryViewModel @Inject constructor(private val loadRatesHistoryUseCase: LoadRatesHistoryUseCase) :
    ViewModel() {

    private var _uiState = MutableStateFlow(HistoryRatesUIStates())
    val uiState = _uiState.asStateFlow()

    private var hasLoadedData = false

    fun loadRatesHistory(baseCurrency: String, symbols: String) {
        if (hasLoadedData) return
        viewModelScope.launch {
            loadRatesHistoryUseCase.execute(
                HistoryRatesRequestParams(
                    baseCurrency = baseCurrency, symbols = symbols
                )
            ).collectLatest { state ->
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
                                success = state.success?.toHistoryRatesUIM().orEmpty(),
                                isLoading = false,
                                error = ""
                            )
                        }
                    }
                }
                hasLoadedData = true
            }
        }
    }

}
