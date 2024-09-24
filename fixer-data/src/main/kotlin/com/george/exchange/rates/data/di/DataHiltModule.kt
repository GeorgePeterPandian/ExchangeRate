package com.george.exchange.rates.data.di

import android.content.Context
import androidx.room.Room
import com.george.exchange.rates.data.RepositoryImpl
import com.george.exchange.rates.data.datasource.local.FixerDao
import com.george.exchange.rates.data.datasource.local.FixerDatabase
import com.george.exchange.rates.data.datasource.local.RoomDS
import com.george.exchange.rates.data.datasource.local.RoomDSImpl
import com.george.exchange.rates.data.datasource.remote.RemoteDS
import com.george.exchange.rates.data.datasource.remote.RemoteDSImpl
import com.george.exchange.rates.data.service.ApiService
import com.george.exchange.rates.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DataModule::class])
internal class DataApiModule {

    @Singleton
    @Provides
    fun provideDataApiService(): ApiService = ApiService()

    @Provides
    fun provideLocalDao(database: FixerDatabase): FixerDao = database.dao

    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context): FixerDatabase =
        Room.databaseBuilder(context, FixerDatabase::class.java, "FixerDatabase")
            .fallbackToDestructiveMigration().build()

}

@InstallIn(SingletonComponent::class)
@Module
internal interface DataModule {

    @Singleton
    @Binds
    fun bindLocalDataSource(fixerRoomDSImpl: RoomDSImpl): RoomDS

    @Singleton
    @Binds
    fun bindRemoteDataSource(remoteDSImpl: RemoteDSImpl): RemoteDS

    @Singleton
    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}