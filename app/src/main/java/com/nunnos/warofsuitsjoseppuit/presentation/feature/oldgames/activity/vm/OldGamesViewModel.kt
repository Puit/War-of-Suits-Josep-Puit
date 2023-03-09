package com.nunnos.warofsuitsjoseppuit.presentation.feature.oldgames.activity.vm

import android.app.Application
import androidx.lifecycle.LiveData
import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameDB
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameEntity
import com.nunnos.warofsuitsjoseppuit.data.repository.OldGameRepository
import com.nunnos.warofsuitsjoseppuit.domain.OldGame
import com.nunnos.warofsuitsjoseppuit.domain.Round
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

    fun getAllRounds(game: String): ArrayList<Round> {
        val roundsList = ArrayList<Round>()
        val roundStrings = game.split("/")
        for (round in roundStrings) {
            getRound(round)?.let { roundsList.add(it) }
        }
        return roundsList
    }

    private fun getRound(roundString: String): Round? {
        try {
            val cardsStringList = roundString.split("(", ")")
            val cardsStringArrayList = ArrayList<String>()

            for (string in cardsStringList) {
                if (string.isNotEmpty()) {
                    cardsStringArrayList.add(string)
                }
            }
            val myCard = Card(
                Card.Type.valueOf(cardsStringArrayList[0].split(",")[0]),
                Card.Number.valueOf(cardsStringArrayList[0].split(",")[1].replace(" ", ""))
            )
            val opponentCard = Card(
                Card.Type.valueOf(cardsStringArrayList[1].split(",")[0]),
                Card.Number.valueOf(cardsStringArrayList[1].split(",")[1].replace(" ", ""))
            )

            return Round(myCard, opponentCard, roundString.endsWith("true"))
        } catch (e: Exception) {
            return null
        }
    }

}