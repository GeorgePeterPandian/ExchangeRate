package com.george.exchange.rates.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.george.exchange.rates.task.composables.ExchangeRatesScreen
import com.george.exchange.rates.task.ui.theme.ExchangeRatesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRatesTheme {
                ExchangeRatesScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExchangeRatesScreenPreview() {
    ExchangeRatesTheme {
        ExchangeRatesScreen()
    }
}