package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class OldGamesNavigationViewModel : ViewModel() {
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