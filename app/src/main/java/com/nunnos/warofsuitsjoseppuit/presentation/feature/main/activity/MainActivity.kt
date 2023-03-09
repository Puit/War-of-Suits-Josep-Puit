package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.databinding.ActivityMainBinding
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm.MainViewModel
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigationManager

class MainActivity : AppCompatActivity() {
    lateinit var databinding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initObservers()
        viewModel.navigateToDashboard()
    }

    private fun initObservers() {
        viewModel.navigation.observe(this) { navigation ->
            MainNavigationManager.goTo(
                this,
                navigation,
                viewModel
            )
        }
    }

    override fun onBackPressed() {
        viewModel.popBackStack()
    }
}