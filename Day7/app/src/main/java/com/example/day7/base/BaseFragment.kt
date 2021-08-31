package com.example.day7.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var controller: NavController
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(layoutInflater, container, false)
        controller = findNavController()

        handleTask()
        return binding.root
    }

    abstract fun getFragmentBinding(layoutInflater: LayoutInflater,container: ViewGroup?,
                                    attachToRoot: Boolean?): VB

    abstract fun handleTask()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}