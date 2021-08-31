package com.example.day7.di

import android.content.Context
import androidx.room.Room
import com.example.day7.data.local.TransactionDatabase
import com.example.day7.data.local.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//This annotation tells Hilt that the dependencies provided via this module shall stay alive as long as application is running.
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TransactionDatabase =
        Room.databaseBuilder(appContext, TransactionDatabase::class.java, TransactionDatabase.DB_NAME).build()

    @Provides
    @Singleton
    fun provideLogDao(database: TransactionDatabase): TransactionDao = database.transactionDao()
}