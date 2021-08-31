package com.example.day7.data.model

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.day7.utils.transaction.TransactionCurrency
import com.example.day7.utils.transaction.TransactionType

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "transaction")
class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var type: TransactionType= TransactionType.SALE,
    val currency: TransactionCurrency= TransactionCurrency.VND,
    val amount: Long= 0,
    val holderName: String= "",
    val time: Long= 0,
) : Serializable {

    @SuppressLint("SimpleDateFormat")
    fun getDateTime(): String {
        val date = Date(time)
        return SimpleDateFormat("yyyy/MM/dd").format(date)
    }
}