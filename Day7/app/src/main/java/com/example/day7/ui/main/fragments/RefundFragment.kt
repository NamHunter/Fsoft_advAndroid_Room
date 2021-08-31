package com.example.day7.ui.main.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day7.base.BaseFragment
import com.example.day7.databinding.FragmentRefundBinding
import com.example.day7.ui.main.MainActivity
import com.example.day7.ui.main.adapter.TransactionAdapter
import com.example.day7.ui.main.dialog.RefundTransactionDialog
import com.example.day7.utils.transaction.TransactionType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RefundFragment : BaseFragment<FragmentRefundBinding>() {

    private val mActivity by lazy { activity as MainActivity }
    private val mViewModel by lazy { mActivity.viewModel }
    private val mAdapter by lazy { TransactionAdapter() }

    override fun handleTask() {
        initView()
        initObserve()
        initListener()
    }

    private fun initListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchTransactions(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchTransactions(newText)
                }
                return true
            }
        })
    }

    private fun searchTransactions(query: String) {
        lifecycle.coroutineScope.launch {
            mViewModel.getFetchTransactionsByHolderName(query).collect {
                val liSaleTransaction = it.filter { item ->
                    item.type == TransactionType.SALE
                }
                mAdapter.differ.submitList(liSaleTransaction)
            }
        }
    }

    private fun initObserve() {
        lifecycle.coroutineScope.launch {
            mViewModel.liTransaction.collect {
                val liSaleTransaction = it.filter { item ->
                    item.type == TransactionType.SALE
                }
                mAdapter.differ.submitList(liSaleTransaction)
            }
        }
    }

    private fun initView() {
        mAdapter.setOnItemClickListener {
            mViewModel.selectedRefundTransaction = it

            RefundTransactionDialog.newInstance()
                .show(childFragmentManager, RefundTransactionDialog.TAG)
        }

        binding.recyclerView.apply {
            adapter = mAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentRefundBinding = FragmentRefundBinding.inflate(
        layoutInflater, container, false
    )
}

