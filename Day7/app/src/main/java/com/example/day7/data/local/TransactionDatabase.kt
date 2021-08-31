package com.example.day7.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.day7.data.local.dao.TransactionDao
import com.example.day7.data.model.Transaction

@Database(entities = [Transaction::class], version = 1)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object{
        const val DB_NAME = "transaction_database.db"
    }
}