package com.example.day7.ui.main.fragments

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day7.base.BaseFragment
import com.example.day7.data.model.Transaction
import com.example.day7.databinding.FragmentShowBinding
import com.example.day7.ui.main.MainActivity
import com.example.day7.ui.main.adapter.TransactionAdapter
import com.example.day7.utils.transaction.TransactionCurrency
import com.example.day7.utils.transaction.TransactionType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShowFragment : BaseFragment<FragmentShowBinding>() {

    private val mActivity by lazy { activity as MainActivity }
    private val mViewModel by lazy { mActivity.viewModel }
    private val mAdapter by lazy { TransactionAdapter() }

    private val liStringFilter = listOf("All", "Sale", "Refund", "USD", "VND")

    override fun handleTask() {
        initView()
        initObserve()
        initListener()
    }

    private fun initView() {
        val adapterSpinner =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, liStringFilter)
        adapterSpinner.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapterSpinner

        binding.recyclerView.apply {
            adapter = mAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initListener() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val liTransaction = mViewModel.liFilterTransactions
                when(position){
                    0 ->{
                        mAdapter.differ.submitList(liTransaction)
                    }
                    1 -> {
                        liTransaction.filter {
                            it.type == TransactionType.SALE
                        }.let {
                            mAdapter.differ.submitList(it)
                        }
                    }
                    2 -> {
                        liTransaction.filter {
                            it.type == TransactionType.REFUND
                        }.let {
                            mAdapter.differ.submitList(it)
                        }
                    }
                    3 -> {
                        liTransaction.filter {
                            it.currency == TransactionCurrency.USD
                        }.let {
                            mAdapter.differ.submitList(it)
                        }
                    }
                    4 -> {
                        liTransaction.filter {
                            it.currency == TransactionCurrency.VND
                        }.let {
                            mAdapter.differ.submitList(it)
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    private fun initObserve() {
        lifecycle.coroutineScope.launch {
            mViewModel.liTransaction.collect {
                mViewModel.liFilterTransactions = it
                mAdapter.differ.submitList(it)
            }
        }
    }

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentShowBinding = FragmentShowBinding.inflate(layoutInflater, container, false)
}