package com.george.exchange.rates.task.uiStates

import com.george.exchange.rates.task.model.HistoryRatesUIM

data class HistoryRatesUIStates(
    val isLoading: Boolean = false,
    val error: String = "",
    var success: List<HistoryRatesUIM>? = null
)
