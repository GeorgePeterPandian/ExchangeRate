package com.george.exchange.rates.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Rates")
data class RatesRM(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 1L,
    @ColumnInfo(name = "base") val currency: String,
    @ColumnInfo(name = "base") val rate: Double
)