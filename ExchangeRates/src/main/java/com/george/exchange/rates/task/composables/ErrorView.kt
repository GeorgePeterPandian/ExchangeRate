package com.george.exchange.rates.task.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.george.exchange.rates.task.R

@Composable
fun ErrorView() {
    Text(
        text = stringResource(id = R.string.text_generic_error),
        style = MaterialTheme.typography.body2,
        modifier = Modifier.padding(16.dp),
        textAlign = TextAlign.Center,
    )
}