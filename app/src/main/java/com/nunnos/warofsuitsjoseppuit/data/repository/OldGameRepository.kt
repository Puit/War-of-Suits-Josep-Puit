package com.nunnos.warofsuitsjoseppuit.data.repository

import androidx.lifecycle.LiveData
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameDao
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameEntity

class OldGameRepository(private val dao: OldGameDao) {
    val readAllData: LiveData<List<OldGameEntity>> = dao.getAll()

    suspend fun addGame(game: OldGameEntity){
        dao.insert(game)
    }
}