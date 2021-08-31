package com.example.day7.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.day7.R
import com.example.day7.base.BaseActivity
import com.example.day7.databinding.ActivityMainBinding
import com.example.day7.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfig: AppBarConfiguration
    val viewModel: MainViewModel by viewModels()

    override fun handleTask() {
        initView()
        initListener()
    }

    private fun initListener() {
        binding.fabRemove.setOnClickListener {
            viewModel.clearDatabase()
        }
    }

    private fun initView() {
        appBarConfig =
            AppBarConfiguration(setOf(R.id.saleFragment, R.id.refundFragment, R.id.showFragment))

        // action bar
        setupActionBarWithNavController(controller, appBarConfig)
        // bottom view
        binding.bottomNavigationView.setupWithNavController(controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun getActivityBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getNavHostFragment(): NavHostFragment =
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
}