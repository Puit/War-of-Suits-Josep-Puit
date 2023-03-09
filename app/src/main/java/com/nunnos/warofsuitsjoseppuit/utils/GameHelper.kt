package com.nunnos.warofsuitsjoseppuit.utils

import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.domain.Round
import com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm.MainViewModel

class GameHelper {
    companion object {
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

        fun createShuffledDeckcards(): List<Card> {
            return createOrderedDeckcards().shuffled()
        }

        fun shuffleSuitsPriority(): ArrayList<Card.Type> {
            val allSuits = ArrayList<Card.Type>()
            for (suitsIndex in Card.Type.values().size - 1 downTo 0 step 1) {
                allSuits.add(Card.Type.values()[suitsIndex])
            }
            return allSuits.shuffled() as ArrayList<Card.Type>
        }

        fun playRound(
            roundIndex: Int,
            myDeck: ArrayList<Card>,
            opponentDeck: ArrayList<Card>,
            suitPriority: ArrayList<Card.Type>
        ): Round {
            if (myDeck.isNotEmpty() && opponentDeck.isNotEmpty() && suitPriority.isNotEmpty()) {
                val isWon =
                    checkIfIWonTheRound(myDeck[roundIndex], opponentDeck[roundIndex], suitPriority)
                return Round(myDeck[roundIndex], opponentDeck[roundIndex], isWon)
            }
            return Round(myDeck[roundIndex], opponentDeck[roundIndex], false)
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
            return false
        }

        fun checkIfIWonTheGame(
            myWonDeck: ArrayList<Card>,
            opponentWonDeck: ArrayList<Card>
        ): MainViewModel.GameResult {
            if (myWonDeck.size > opponentWonDeck.size) {
                return MainViewModel.GameResult.WIN
            } else if (myWonDeck.size < opponentWonDeck.size) {
                return MainViewModel.GameResult.LOSE
            }
            return MainViewModel.GameResult.TIE
        }
    }
}