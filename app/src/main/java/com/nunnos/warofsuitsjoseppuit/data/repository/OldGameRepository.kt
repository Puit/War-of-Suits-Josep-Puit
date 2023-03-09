package com.nunnos.warofsuitsjoseppuit.data.repository

import androidx.lifecycle.LiveData
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameDao
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameEntity
import com.nunnos.warofsuitsjoseppuit.domain.OldGame
import com.nunnos.warofsuitsjoseppuit.domain.mapper.OldGameMapper

class OldGameRepository(private val dao: OldGameDao) {
    val readAlldata: LiveData<List<OldGameEntity>> = dao.getAll()

    fun addGame(game: OldGame) {
        dao.insert(OldGameMapper.map(game))
    }
}