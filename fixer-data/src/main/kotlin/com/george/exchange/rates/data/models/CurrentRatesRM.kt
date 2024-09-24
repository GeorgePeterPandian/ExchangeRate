package com.george.exchange.rates.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.george.exchange.rates.data.datasource.local.FixerCurrentRatesTypeConverters

@Entity(tableName = "CurrentRates")
@TypeConverters(FixerCurrentRatesTypeConverters::class)
data class CurrentRatesRM(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 1L,
    @ColumnInfo(name = "base") val base: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "rates") val rates: List<RatesRM>,
    @ColumnInfo(name = "success") val success: Boolean,
    @ColumnInfo(name = "timeStamp") val timestamp: Long,
    @ColumnInfo(name = "storedTimestamp") var storedTimestamp: Long
)