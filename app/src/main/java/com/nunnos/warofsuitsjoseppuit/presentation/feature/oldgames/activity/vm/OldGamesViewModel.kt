package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm

import android.app.Application
import androidx.lifecycle.LiveData
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameDB
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameEntity
import com.nunnos.warofsuitsjoseppuit.data.repository.OldGameRepository
import com.nunnos.warofsuitsjoseppuit.domain.OldGame
import com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.navigation.OldGamesNavigationViewModel

class OldGamesViewModel(application: Application) : OldGamesNavigationViewModel(application) {
    lateinit var selectedGame: OldGame
    private val repository: OldGameRepository
    val readAllData: LiveData<List<OldGameEntity>>

    init {
        val dao = OldGameDB.getInstance(application).getDao()
        repository = OldGameRepository(dao)
        readAllData = repository.readAlldata
    }
}