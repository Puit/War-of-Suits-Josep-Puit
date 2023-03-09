package com.nunnos.warofsuitsjoseppuit.utils

import com.nunnos.warofsuitsjoseppuit.data.Card
import com.nunnos.warofsuitsjoseppuit.data.Card.Companion.MAX_CARDS
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

        fun calculateScore(
            deck: List<Round>,
            suitPriority: ArrayList<Card.Type>,
            position: Int,
            isMyScore: Boolean
        ): Int {
            var myScore = 0
            var opponentScore = 0
            for (index in 0..position) {
                if (checkIfIWonTheRound(
                        deck[index].myCard,
                        deck[index].oppponentCard,
                        suitPriority
                    )
                ) {
                    myScore += 2
                } else {
                    opponentScore += 2
                }
            }
            if (isMyScore) {
                return myScore
            } else {
                return opponentScore
            }
        }

        fun getMaxScore(
            deck: List<Round>,
            suitPriority: ArrayList<Card.Type>,
            isMyScore: Boolean
        ): Int {
            return calculateScore(
                deck,
                suitPriority,
                (MAX_CARDS / 2) - 1,
                isMyScore
            )
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
}