package com.george.exchange.rates.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.george.exchange.rates.data.datasource.local.FixerDao
import com.george.exchange.rates.data.models.CurrentRatesRM

@Database(entities = [CurrentRatesRM::class], version = 1, exportSchema = false)
internal abstract class FixerDatabase : RoomDatabase() {
    abstract val dao: FixerDao
}