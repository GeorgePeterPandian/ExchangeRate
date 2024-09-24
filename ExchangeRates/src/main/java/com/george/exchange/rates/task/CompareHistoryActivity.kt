package com.george.exchange.rates.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.george.exchange.rates.task.composables.CompareHistoryScreen
import com.george.exchange.rates.task.ui.theme.ExchangeRatesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CompareHistoryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val baseAmount = intent.getStringExtra("baseAmount").orEmpty()
        val baseCurrency = intent.getStringExtra("baseCurrency").orEmpty()
        val baseCountryCode = intent.getStringExtra("baseCountryCode").orEmpty()
        val currencyOne = intent.getStringExtra("currencyOne").orEmpty()
        val countryCodeOne = intent.getStringExtra("countryCodeOne").orEmpty()
        val currencyTwo = intent.getStringExtra("currencyTwo").orEmpty()
        val countryCodeTwo = intent.getStringExtra("countryCodeTwo").orEmpty()
        setContent {
            ExchangeRatesTheme {
                CompareHistoryScreen(
                    baseAmount = baseAmount,
                    baseCurrency = baseCurrency,
                    baseCountryCode = baseCountryCode,
                    currencyOne = currencyOne,
                    countryCodeOne = countryCodeOne,
                    currencyTwo = currencyTwo,
                    countryCodeTwo = countryCodeTwo
                )
            }
        }
    }
}