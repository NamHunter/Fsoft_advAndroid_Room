package com.example.day7.ui.main.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.day7.data.model.Transaction
import com.example.day7.databinding.ItemTransactionBinding

class TransactionAdapter :
    RecyclerView.Adapter<TransactionAdapter.CategoryViewHolder>() {

    private var mOnItemClickListener: ((Transaction) -> Unit)? = null

    fun setOnItemClickListener(listener: ((Transaction) -> Unit)?) {
        mOnItemClickListener = listener
    }

    private val differCallback = object : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: Transaction, newItem: Transaction): Any? {
            return super.getChangePayload(oldItem, newItem)
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class CategoryViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(myTransaction: Transaction) {
            binding.root.setOnClickListener {
                mOnItemClickListener?.let { it1 -> it1(myTransaction) }
            }
            binding.apply {
                transaction = myTransaction
            }
        }
    }
}