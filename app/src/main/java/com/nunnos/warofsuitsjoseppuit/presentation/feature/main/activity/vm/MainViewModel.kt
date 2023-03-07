package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.data.Card.Companion.MAX_CARDS
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.navigation.vm.MainNavigationViewModel

class MainViewModel : MainNavigationViewModel() {
    enum class GameResult {
        WIN, LOSE, TIE
    }

    private val TAG = this.javaClass.simpleName
    lateinit var suitPriority: ArrayList<Card.Type>
    lateinit var myDeck: ArrayList<Card>
    lateinit var opponentDeck: ArrayList<Card>
    var round = 0
    var myWonDeck: MutableLiveData<ArrayList<Card>> = MutableLiveData()
    var opponentWonDeck: MutableLiveData<ArrayList<Card>> = MutableLiveData()

    //TODO: TEST: comprobar que no hay más de 52,
    //TODO: TEST: comprobar que cada uno tiene 26
    //TODO: TEST: comprobar que MyCard no repite cartas
    //TODO: TEST: comprobar que OponentCards no repite cartas
    //TODO: TEST: comprobar que entre Mycards y OponentCards no repite cartas

    fun dealCards() {
        val shuffledDeckOfCards = createOrderedDeckcards().shuffled()
        myDeck = shuffledDeckOfCards.drop(0).take((MAX_CARDS / 2)) as ArrayList<Card>
        opponentDeck =
            shuffledDeckOfCards.drop((MAX_CARDS / 2)).take(MAX_CARDS - 1) as ArrayList<Card>
        myWonDeck.value = ArrayList()
        opponentWonDeck.value = ArrayList()
        round = 0
        Log.d(TAG, "myDeck: " + myDeck.size)
        Log.d(TAG, "opponentDeck: " + opponentDeck.size)
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

    //TODO: TEST: comprobar que hay siempre 4 tipos
    //TODO: TEST: comprobar que mezcla cada vez
    fun shuffleSuitsPriority() {
        val allSuits = ArrayList<Card.Type>()
        for (suitsIndex in Card.Type.values().size - 1 downTo 0 step 1) {
            allSuits.add(Card.Type.values()[suitsIndex])
        }
        suitPriority = allSuits.shuffled() as ArrayList<Card.Type>
        Log.d(TAG, "Suit priority: " + suitPriority)
    }


    fun playOneRound() {
        if (myDeck.isNotEmpty() && opponentDeck.isNotEmpty() && suitPriority.isNotEmpty()) {
            if (checkIfIWonTheRound(myDeck[0], opponentDeck[0], suitPriority)) {
                addCard(myWonDeck, myDeck[0])
                addCard(myWonDeck, opponentDeck[0])
                Log.d(
                    TAG, "playOneRound: " + "I win " + "myCard: " + myDeck[0] +
                            " opponentDeck: " + opponentDeck[0] +
                            " suitPriority: " + suitPriority
                )
            } else {
                addCard(opponentWonDeck, myDeck[0])
                addCard(opponentWonDeck, opponentDeck[0])
                Log.d(
                    TAG, "playOneRound: " + "I lose " + "myCard: " + myDeck[0] +
                            " opponentDeck: " + opponentDeck[0] +
                            " suitPriority: " + suitPriority
                )
            }
            myDeck.remove(myDeck[0])
            opponentDeck.remove(opponentDeck[0])
        }
        round++
    }

    private fun addCard(deck: MutableLiveData<ArrayList<Card>>, card: Card) {
        if (deck.value != null) {
            val oldList = deck.value!!
            oldList.add(card)
            deck.value = oldList
        }
    }

    fun checkIfIWonTheRound(
        myCard: Card,
        opponentCard: Card,
        suitPriority: ArrayList<Card.Type>
    ): Boolean {
        if (myCard.number > opponentCard.number) {
            return true
        } else if (myCard.number < opponentCard.number) {
            return false
        }
        for (suit in suitPriority) {
            if (myCard.type == suit) {
                return true
            }
            if (opponentCard.type == suit) {
                return false
            }
        }
        Log.e(
            TAG,
            "checkIfIWin: Something wrong happened" + "myCard: " + myCard + "opponentCard: " + opponentCard + "suitPriority: " + suitPriority
        )
        return false
    }

    fun haveToOrganizeTheGame(): Boolean {
        return !(::myDeck.isInitialized && ::opponentDeck.isInitialized) || myDeck.isEmpty() && opponentDeck.isEmpty()
    }

    fun isGameFinished(): Boolean {
        return myDeck.isEmpty() && opponentDeck.isEmpty() && myWonDeck.value!!.size + opponentWonDeck.value!!.size == MAX_CARDS
    }

    fun checkIfIWonTheGame(
        myWonDeck: ArrayList<Card>,
        opponentWonDeck: ArrayList<Card>
    ): GameResult {
        if (myWonDeck.size > opponentWonDeck.size) {
            return GameResult.WIN
        } else if (myWonDeck.size < opponentWonDeck.size) {
            return GameResult.LOSE
        }
        return GameResult.TIE
    }
    fun clearData(){
        suitPriority= ArrayList<Card.Type>()
        myDeck= ArrayList<Card>()
        opponentDeck= ArrayList<Card>()
        round = 0
        myWonDeck.value = ArrayList()
        opponentWonDeck.value = ArrayList()
    }
}