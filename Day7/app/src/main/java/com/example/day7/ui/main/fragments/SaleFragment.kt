package com.example.day7.ui.main.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.day7.base.BaseFragment
import com.example.day7.data.model.Transaction
import com.example.day7.databinding.FragmentSaleBinding
import com.example.day7.ui.main.MainActivity
import com.example.day7.utils.transaction.TransactionCurrency
import com.example.day7.utils.transaction.TransactionType
import java.util.*

class SaleFragment : BaseFragment<FragmentSaleBinding>() {

    private val mActivity by lazy { activity as MainActivity }
    private val mViewModel by lazy { mActivity.viewModel }

    override fun handleTask() {
        initListener()
    }

    private fun initListener() {
        binding.btnSale.setOnClickListener {
            val transaction = Transaction(
                type = TransactionType.SALE,
                currency = if (binding.radioUSD.isChecked) TransactionCurrency.USD else TransactionCurrency.VND,
                amount = binding.txtAmount.text.toString().toLong(),
                holderName = binding.txtHolderName.text.toString(),
                time = Date().time
            )

            mViewModel.insertTransaction(transaction)
            Toast.makeText(
                requireContext(),
                "Succesfully add saled transaction",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getFragmentBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentSaleBinding = FragmentSaleBinding.inflate(layoutInflater, container, false)
}