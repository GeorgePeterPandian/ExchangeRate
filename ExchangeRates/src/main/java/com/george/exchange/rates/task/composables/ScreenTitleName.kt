package com.george.exchange.rates.task.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ScreenTitleName(title: String, modifier: Modifier = Modifier) {
    Column(Modifier.background(color = MaterialTheme.colors.primary)) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}