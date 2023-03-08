package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm

import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGame
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigationViewModel

class OldGamesViewModel : OldGamesNavigationViewModel() {
    lateinit var selectedGame: OldGame
}