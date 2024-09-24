package com.george.exchange.rates.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.george.exchange.rates.data.models.CurrentRatesRM
import kotlinx.coroutines.flow.Flow

@Dao
interface FixerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentRates(currentRatesRM: CurrentRatesRM)

    @Query("SELECT * FROM CurrentRates")
    fun loadCurrentRates(): Flow<CurrentRatesRM>

    @Query("DELETE FROM CurrentRates")
    suspend fun deleteCurrentRates()

    @Query("SELECT storedTimestamp FROM CurrentRates")
    suspend fun loadLocalStoredTimestamp(): Long?
}