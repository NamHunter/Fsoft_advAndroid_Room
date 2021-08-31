package com.example.day7.data.repository

import com.example.day7.base.BaseRepository
import com.example.day7.data.local.dao.TransactionDao
import com.example.day7.data.model.Transaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao):
    BaseRepository() {

     fun getAllTransactions() =
         transactionDao.getAllTransaction()

    fun getTransactionsByHolderName(holderName: String) =
        transactionDao.getTransactionByHolderName(holderName)

    suspend fun insertTransaction(transaction: Transaction) =
        transactionDao.insertTransaction(transaction)

    suspend fun clearDatabase() = transactionDao.clearDatabase()

    suspend fun updateTypeTransaction(transaction: Transaction) {
        val a = transactionDao.updateTypeTransaction(transaction)
        return transactionDao.updateTypeTransaction(transaction)
    }

}