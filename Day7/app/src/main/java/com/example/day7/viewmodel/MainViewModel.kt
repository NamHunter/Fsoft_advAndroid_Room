package com.example.day7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.day7.data.model.Transaction
import com.example.day7.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TransactionRepository) :
    ViewModel() {

    var liFilterTransactions = listOf<Transaction>()
    var selectedRefundTransaction = Transaction()

    val liTransaction =
        repository.getAllTransactions()

    fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.insertTransaction(transaction)
    }

    fun clearDatabase() = viewModelScope.launch {
        repository.clearDatabase()
    }

    fun updateTypeTransaction() = viewModelScope.launch {
        repository.updateTypeTransaction(selectedRefundTransaction)
    }

    fun getFetchTransactionsByHolderName(holderName: String) =
        repository.getTransactionsByHolderName(holderName)
}