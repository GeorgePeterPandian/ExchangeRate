package com.george.exchange.rates.task.composables

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.george.exchange.rates.task.R
import com.murgupluoglu.flagkit.FlagKit

@Composable
fun CountryFlag(countryCode: String, size: DpSize, modifier: Modifier) {
    val context = LocalContext.current
    var flagDrawableId = 0
    try {
        flagDrawableId = FlagKit.getResId(context, countryCode)
    } catch (exception: Resources.NotFoundException) {
        Log.i("Exchange Rates", exception.localizedMessage.orEmpty())
    }
    if (flagDrawableId > 0) {
        Image(
            painter = painterResource(id = flagDrawableId),
            contentDescription = "Flag of $countryCode",
            modifier = modifier
                .size(size)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    } else {
        Text(
            stringResource(id = R.string.flag_to_be_added),
            style = MaterialTheme.typography.caption,
            modifier = modifier
                .padding(start = 2.dp, top = 16.dp, end = 8.dp, bottom = 16.dp)
                .width(32.dp),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCurrencyFlagScreen() {
    CountryFlag(
        countryCode = "US", size = DpSize(width = 40.dp, height = 40.dp), modifier = Modifier
    )
}