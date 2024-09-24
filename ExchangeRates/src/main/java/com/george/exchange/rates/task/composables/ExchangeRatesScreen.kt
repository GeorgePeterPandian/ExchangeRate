package com.george.exchange.rates.task.composables

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.george.exchange.rates.task.CompareHistoryActivity
import com.george.exchange.rates.task.R
import com.george.exchange.rates.task.model.RateUIM
import com.george.exchange.rates.task.viewModel.LoadCurrentRatesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(FlowPreview::class)
@Composable
fun ExchangeRatesScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<LoadCurrentRatesViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (viewModel.selectedCurrencies.size == 2) {
        ExchangeRateComparisonDialog(viewModel.selectedCurrencies,
            baseCurrency = uiState.success?.base.orEmpty(),
            baseAmount = viewModel.baseAmount,
            baseCountryCode = uiState.success?.countryCode.orEmpty(),
            onDismiss = {
                viewModel.onResetSelections()
            })
    }

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        item {
            ScreenTitleName(title = stringResource(id = R.string.app_name).uppercase())
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
                    modifier = modifier
                        .fillParentMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 16.dp, top = 8.dp)
                ) {
                    CountryFlag(
                        countryCode = uiState.success?.countryCode.orEmpty(),
                        size = DpSize(width = 30.dp, height = 30.dp),
                        modifier = modifier
                            .weight(.1f)
                            .padding(top = 24.dp)
                    )
                    BaseCurrencyValue(
                        modifier = modifier
                            .weight(.2f)
                            .align(Alignment.CenterVertically)
                            .padding(top = 8.dp, start = 18.dp),
                        baseCurrency = uiState.success?.base.orEmpty(),
                    )
                    OutlinedTextField(value = viewModel.baseAmount,
                        onValueChange = { viewModel.baseAmount = it },
                        label = {
                            Text(
                                stringResource(id = R.string.enter_amount),
                                style = MaterialTheme.typography.body1
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.7f)
                            .padding(start = 14.dp, end = 16.dp),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.body2,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }
            items(success.rates) { rate ->
                val isSelected = viewModel.selectedCurrencies.contains(rate)
                RateRow(modifier,
                    rate,
                    baseAmount = viewModel.baseAmount.toDoubleOrNull() ?: 1.0,
                    isSelected = isSelected,
                    onSelect = { selectedRate ->
                        when {
                            isSelected -> viewModel.selectedCurrencies -= selectedRate
                            viewModel.selectedCurrencies.size < 2 -> viewModel.selectedCurrencies += selectedRate
                        }
                    })
            }
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ExchangeRateComparisonDialog(
    selectedCurrencies: List<RateUIM>,
    baseCurrency: String,
    baseAmount: String,
    baseCountryCode: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(onDismissRequest = { onDismiss() }, title = {
        Text(
            stringResource(id = R.string.compare), style = MaterialTheme.typography.body1
        )
    }, text = {
        Column {
            Text(
                text = "${selectedCurrencies[0].currency} and ${selectedCurrencies[1].currency} to $baseAmount  $baseCurrency",
                style = MaterialTheme.typography.body1
            )

        }
    }, confirmButton = {
        Button(onClick = {
            onDismiss()
            val intent = Intent(context, CompareHistoryActivity::class.java)
            intent.putExtra("baseAmount", baseAmount)
            intent.putExtra("baseCurrency", baseCurrency)
            intent.putExtra("baseCountryCode", baseCountryCode)
            intent.putExtra("currencyOne", selectedCurrencies.first().currency)
            intent.putExtra("countryCodeOne", selectedCurrencies.first().countryCode)
            intent.putExtra("currencyTwo", selectedCurrencies.last().currency)
            intent.putExtra("countryCodeTwo", selectedCurrencies.last().countryCode)
            context.startActivity(intent)
        }) {
            Text(
                stringResource(id = R.string.ok).uppercase(), style = MaterialTheme.typography.button
            )
        }
    }, dismissButton = {
        Button(onClick = onDismiss) {
            Text(
                stringResource(id = R.string.cancel).uppercase(), style = MaterialTheme.typography.button
            )
        }
    })
}
