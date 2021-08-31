package com.example.day7.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.day7.data.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * from `transaction`")
    fun getAllTransaction(): Flow<List<Transaction>>

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("delete from `transaction`")
    suspend fun clearDatabase()

    @Query("SELECT * from `transaction` WHERE holderName LIKE '%' || :holderName || '%'")
    fun getTransactionByHolderName(holderName: String): Flow<List<Transaction>>

    @Update
    suspend fun updateTypeTransaction(transaction: Transaction)
}