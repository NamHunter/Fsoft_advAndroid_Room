package com.example.day7.ui.main.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.day7.databinding.DialogRefundTransactionBinding
import com.example.day7.ui.main.MainActivity
import com.example.day7.utils.transaction.TransactionType

class RefundTransactionDialog : DialogFragment() {

    private lateinit var binding: DialogRefundTransactionBinding

    private val mActivity by lazy {
        activity as MainActivity
    }

    private val mViewModel by lazy {
        mActivity.viewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogRefundTransactionBinding.inflate(layoutInflater)

        handleTask()

        return AlertDialog.Builder(mActivity).apply {
            setView(binding.root)
        }.create()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(InsetDrawable(ColorDrawable(Color.TRANSPARENT), 0))
    }

    private fun handleTask() {
        initListener()
        initView()
    }

    private fun initView() {
        binding.transaction = mViewModel.selectedRefundTransaction
    }

    private fun initListener() {
        binding.btnOK.setOnClickListener {
            mViewModel.selectedRefundTransaction.type = TransactionType.REFUND
            mViewModel.updateTypeTransaction()
            Toast.makeText(mActivity, "Successfully refund transaction", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val TAG = "refund_transaction"

        fun newInstance() = RefundTransactionDialog()
    }
}