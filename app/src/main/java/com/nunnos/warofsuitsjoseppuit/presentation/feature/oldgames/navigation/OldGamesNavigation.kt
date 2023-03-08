package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation

import androidx.annotation.IntDef
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigation.Companion.DISTRIBUTOR
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigation.Companion.OLD_GAME
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigation.Companion.POP_BACKSTACK

@IntDef(DISTRIBUTOR, OLD_GAME, POP_BACKSTACK)
@Retention(AnnotationRetention.SOURCE)
annotation class OldGamesNavigation {
    companion object {
        const val DISTRIBUTOR = 0
        const val OLD_GAME = 1
        const val POP_BACKSTACK = 2
    }
}