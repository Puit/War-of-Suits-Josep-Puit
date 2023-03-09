package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nunnos.warofsuitsjoseppuit.R
import com.nunnos.warofsuitsjoseppuit.databinding.ActivityOldGamesBinding
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm.OldGamesViewModel
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigationManager

class OldGamesActivity : AppCompatActivity() {
    lateinit var databinding: ActivityOldGamesBinding
    lateinit var viewModel: OldGamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old_games)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_old_games)
        viewModel = ViewModelProvider(this)[OldGamesViewModel::class.java]
        initObservers()
        viewModel.navigateToDistributor()
    }

    private fun initObservers() {
        viewModel.navigation.observe(this) { navigation ->
            OldGamesNavigationManager.goTo(
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