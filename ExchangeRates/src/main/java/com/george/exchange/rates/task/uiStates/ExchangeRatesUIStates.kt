package com.george.exchange.rates.task.uiStates

import com.george.exchange.rates.task.model.CurrentRatesUIM

data class ExchangeRatesUIStates(
    val isLoading: Boolean = false,
    val error: String = "",
    var success: CurrentRatesUIM? = null
)
