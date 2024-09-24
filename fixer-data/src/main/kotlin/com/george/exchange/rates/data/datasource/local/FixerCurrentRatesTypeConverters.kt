package com.george.exchange.rates.data.datasource.local

import androidx.room.TypeConverter
import com.george.exchange.rates.data.models.RatesRM
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FixerCurrentRatesTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromRatesList(rates: List<RatesRM>?): String? {
        return rates?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toRatesList(ratesString: String?): List<RatesRM>? {
        return ratesString?.let {
            val type = object : TypeToken<List<RatesRM>>() {}.type
            gson.fromJson(it, type)
        }
    }
}
