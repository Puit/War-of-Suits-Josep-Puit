package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm

import android.util.Log
import com.nunnos.warofsuits.data.Card
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.vm.MainNavigationViewModel

class MainViewModel : MainNavigationViewModel() {
    val MAX_CARDS = 52
    lateinit var myDeck: ArrayList<Card>
    lateinit var opponentDeck: ArrayList<Card>

    //TODO: TEST: comprobar que no hay más de 52,
    //TODO: TEST: comprobar que cada uno tiene 26
    //TODO: TEST: comprobar que MyCard no repite cartas
    //TODO: TEST: comprobar que OponentCards no repite cartas
    //TODO: TEST: comprobar que entre Mycards y OponentCards no repite cartas

    fun dealCards() {
        val shuffledDeckOfCards = createOrderedDeckcards().shuffled()
        myDeck = shuffledDeckOfCards.drop(0).take((MAX_CARDS / 2)) as ArrayList<Card>
        opponentDeck =
            shuffledDeckOfCards.drop((MAX_CARDS / 2) ).take(MAX_CARDS - 1) as ArrayList<Card>
        Log.d("TAG", "MYCARDS: " + myDeck.size)
        Log.d("TAG", "OPPONENTCARDS: " + opponentDeck.size)
    }

    fun createOrderedDeckcards(): ArrayList<Card> {
        val deckOfCards = ArrayList<Card>()
        for (suitsIndex in Card.Type.values().size - 1 downTo 0 step 1) {
            for (numberIndex in Card.Number.values().size - 1 downTo 0 step 1) {
                deckOfCards.add(
                    Card(Card.Type.values()[suitsIndex], Card.Number.values()[numberIndex])
                )
            }
        }
        return deckOfCards
    }

}