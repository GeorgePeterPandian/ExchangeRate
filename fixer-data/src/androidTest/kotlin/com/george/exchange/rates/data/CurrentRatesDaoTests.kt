package com.george.exchange.rates.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.george.exchange.rates.data.datasource.local.FixerDao
import com.george.exchange.rates.data.datasource.local.FixerDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrentRatesDaoTests {

    private lateinit var db: FixerDatabase
    private lateinit var currentRatesDao: FixerDao

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context, FixerDatabase::class.java
        ).build()
        currentRatesDao = db.dao
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndLoadCurrentRates() = runBlocking {
        // Prepare test data
        val currentRates = currentRatesRM

        // Insert data
        currentRatesDao.insertCurrentRates(currentRates)

        // Load and verify data
        val loadedRates = currentRatesDao.loadCurrentRates().first()
        assertEquals(currentRates, loadedRates)
    }

    @Test
    fun testDeleteCurrentRates() = runBlocking {
        // Prepare test data
        val currentRates = currentRatesRM

        // Insert data
        currentRatesDao.insertCurrentRates(currentRates)

        // Delete data
        currentRatesDao.deleteCurrentRates()

        // Verify that no data is present
        val loadedRates = currentRatesDao.loadCurrentRates().first()
        assertEquals(null, loadedRates)
    }

    @Test
    fun testLoadLocalStoredTimestamp() = runBlocking {
        // Prepare test data with a timestamp
        val timestamp = System.currentTimeMillis()
        val currentRates = currentRatesRM.copy(storedTimestamp = timestamp)
        currentRatesDao.insertCurrentRates(currentRates)

        // Load and verify timestamp
        val loadedTimestamp = currentRatesDao.loadLocalStoredTimestamp()
        assertEquals(timestamp, loadedTimestamp)
    }

}