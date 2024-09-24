package com.george.exchange.rates.task.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.george.exchange.rates.task.model.RateUIM
import java.util.Locale

@Composable
fun RateRow(
    modifier: Modifier,
    rate: RateUIM,
    baseAmount: Double,
    isSelected: Boolean,
    onSelect: (RateUIM) -> Unit
) {
    val backgroundColor = if (isSelected) Color.LightGray else Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect(rate) }
            .background(backgroundColor),
    ) {
        CountryFlag(
            countryCode = rate.countryCode,
            size = DpSize(width = 40.dp, height = 40.dp),
            modifier = modifier.padding(top = 2.5.dp)
        )
        Text(
            text = rate.currency.uppercase(),
            modifier = modifier
                .padding(start = 16.dp, top = 12.dp, end = 8.dp, bottom = 5.dp)
                .weight(.2f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = String.format(Locale.getDefault(), "%.6f", rate.rate * baseAmount),
            modifier = modifier
                .padding(start = 16.dp, top = 12.dp, end = 8.dp, bottom = 5.dp)
                .weight(.6f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
    Divider(
        thickness = .5.dp,
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray
    )
}


