package com.george.exchange.rates.task.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.george.exchange.rates.task.R
import com.george.exchange.rates.task.viewModel.LoadRatesHistoryViewModel
import kotlinx.coroutines.FlowPreview
import java.util.Locale

@OptIn(FlowPreview::class)
@Composable
fun CompareHistoryScreen(
    baseAmount: String,
    baseCurrency: String,
    baseCountryCode: String,
    currencyOne: String,
    countryCodeOne: String,
    currencyTwo: String,
    countryCodeTwo: String
) {
    val viewModel = viewModel<LoadRatesHistoryViewModel>()

    viewModel.loadRatesHistory(
        baseCurrency = baseCurrency, symbols = listOf(currencyOne, currencyTwo).joinToString(",")
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        item {
            ScreenTitleName(title = stringResource(id = R.string.history_rates).uppercase())
        }
        item {
            when {
                uiState.isLoading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillParentMaxSize()
                            .background(Color.Transparent)
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        item {
            when {
                uiState.error.isNotEmpty() -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillParentMaxSize()
                            .background(Color.Transparent)
                    ) {
                        ErrorView()
                    }
                }
            }
        }

        uiState.success?.let { success ->
            item {
                Row(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 16.dp)
                ) {
                    CountryFlag(
                        countryCode = baseCountryCode,
                        size = DpSize(width = 30.dp, height = 30.dp),
                        modifier = Modifier
                            .weight(.2f)
                            .padding(start = 4.dp, top = 18.dp)
                    )
                    BaseCurrencyValue(
                        modifier = Modifier
                            .weight(.2f)
                            .align(Alignment.CenterVertically)
                            .padding(top = 16.dp),
                        baseCurrency = baseCurrency,
                    )
                    Text(
                        text = baseAmount,
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .weight(.8f),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.date),
                        modifier = Modifier
                            .padding(start = 4.dp, top = 28.dp)
                            .weight(.3f),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                    )
                    CountryFlag(
                        countryCode = countryCodeOne,
                        size = DpSize(width = 30.dp, height = 30.dp),
                        modifier = Modifier
                            .weight(.15f)
                            .padding(start = 16.dp, top = 20.dp)
                    )
                    Text(
                        text = currencyOne,
                        modifier = Modifier
                            .padding(top = 28.dp)
                            .weight(.2f),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                    )

                    CountryFlag(
                        countryCode = countryCodeTwo,
                        size = DpSize(width = 30.dp, height = 30.dp),
                        modifier = Modifier
                            .weight(.15f)
                            .padding(start = 16.dp, top = 20.dp)
                    )
                    Text(
                        text = currencyTwo,
                        modifier = Modifier
                            .padding(top = 28.dp)
                            .weight(.2f),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Divider(
                    thickness = .5.dp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    color = Color.LightGray
                )
            }
            items(success) { rate ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = rate.date,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 28.dp)
                            .weight(1f),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                    )
                    Text(
                        text = String.format(
                            Locale.getDefault(),
                            "%.2f",
                            rate.rates.first().times(baseAmount.toDouble())
                        ),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 28.dp)
                            .weight(1f),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                    )

                    Text(
                        text = String.format(
                            Locale.getDefault(),
                            "%.6f",
                            rate.rates.last().times(baseAmount.toDouble())
                        ),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 28.dp)
                            .weight(1f),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Divider(
                    thickness = .5.dp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    color = Color.LightGray
                )
            }
        }
    }
}