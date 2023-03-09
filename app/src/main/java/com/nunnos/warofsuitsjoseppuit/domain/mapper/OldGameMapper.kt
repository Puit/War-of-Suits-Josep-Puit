package com.nunnos.warofsuitsjoseppuit.domain.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameEntity
import com.nunnos.warofsuitsjoseppuit.domain.OldGame

class OldGameMapper {
    companion object {

        fun map(entity: OldGameEntity): OldGame {
            return OldGame(
                entity.result,
                getSuitsFromString(entity.suits),
                entity.date,
                entity.time,
                entity.game
            )
        }

        fun map(model: OldGame): OldGameEntity {
            return OldGameEntity(
                model.result,
                getStringFromSuits(model.suits),
                model.date,
                model.time,
                model.game
            )
        }

        fun getSuitsFromString(text: String): ArrayList<Card.Type> {
            val gson = Gson()
            val listType = object : TypeToken<ArrayList<Card.Type?>?>() {}.type
            return gson.fromJson(text, listType)
        }

        fun getStringFromSuits(suitsList: ArrayList<Card.Type>): String {
            val gson = Gson()
            return gson.toJson(suitsList)
        }
    }
}