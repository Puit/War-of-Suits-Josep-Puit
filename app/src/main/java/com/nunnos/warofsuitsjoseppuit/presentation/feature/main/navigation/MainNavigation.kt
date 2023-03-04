package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation

import androidx.annotation.IntDef
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.DASHBOARD
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.MainNavigation.Companion.GAME


@IntDef(DASHBOARD, GAME)
    @Retention(AnnotationRetention.SOURCE)
    annotation class MainNavigation {
        companion object {
            const val DASHBOARD = 0
            const val GAME = 1

        }
    }