package com.george.exchange.rates.task.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BaseCurrencyValue(modifier: Modifier, baseCurrency: String) {
    Text(
        text = baseCurrency,
        modifier = modifier.padding(start = 8.dp, end = 16.dp),
        textAlign = TextAlign.Center,
        maxLines = 1,
        style = MaterialTheme.typography.body1,
    )
}