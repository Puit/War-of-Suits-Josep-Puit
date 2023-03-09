package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation

import androidx.annotation.IntDef
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.DASHBOARD
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.GAME
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.OLD_GAMES
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.POP_BACKSTACK


@IntDef(DASHBOARD, GAME, OLD_GAMES, POP_BACKSTACK)
@Retention(AnnotationRetention.SOURCE)
annotation class MainNavigation {
    companion object {
        const val DASHBOARD = 0
        const val GAME = 1
        const val OLD_GAMES = 2
        const val POP_BACKSTACK = 3
    }
}