package com.george.exchange.rates.core.domain

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getPastFiveDates(): List<String> {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    val dates = mutableListOf<String>()
    for (i in 0..4) {
        val date = calendar.time
        dates.add(dateFormat.format(date))
        calendar.add(Calendar.DAY_OF_YEAR, -1)
    }
    return dates
}