package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation

open class MainNavigationViewModel(application: Application) : AndroidViewModel(application) {
    open var navigation = MutableLiveData<Int>()

    open fun navigateToDashboard() {
        navigation.value = MainNavigation.DASHBOARD
    }

    open fun navigateToGame() {
        navigation.value = MainNavigation.GAME
    }

    open fun navigateToOldGames() {
        navigation.value = MainNavigation.OLD_GAMES
    }

    open fun popBackStack() {
        navigation.value = MainNavigation.POP_BACKSTACK
    }
}