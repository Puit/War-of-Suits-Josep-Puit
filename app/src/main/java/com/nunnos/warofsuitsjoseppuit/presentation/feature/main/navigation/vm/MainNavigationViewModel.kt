package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation

open class MainNavigationViewModel : ViewModel() {
    open var navigation = MutableLiveData<Int>()

    open fun navigateToDashboard() {
        navigation.value = MainNavigation.DASHBOARD
    }

    open fun navigateToGame() {
        navigation.value = MainNavigation.GAME
    }
    open fun popBackStack() {
        navigation.value = MainNavigation.POP_BACKSTACK
    }
}