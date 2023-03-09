package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class OldGamesNavigationViewModel(application: Application) : AndroidViewModel(application) {
    open var navigation = MutableLiveData<Int>()

    open fun navigateToDistributor() {
        navigation.value = OldGamesNavigation.DISTRIBUTOR
    }

    open fun navigateToOldGame() {
        navigation.value = OldGamesNavigation.OLD_GAME
    }

    open fun popBackStack() {
        navigation.value = OldGamesNavigation.POP_BACKSTACK
    }
}