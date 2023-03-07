package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.nunnos.warofsuitsjoseppuit.data.Card
import junit.framework.Assert
import junit.framework.JUnit4TestAdapter
import org.junit.Assert.assertEquals


import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class MainViewModelTest {
    private lateinit var SUT: MainViewModel

    @Before fun setUp() {
        SUT = MainViewModel()
    }

    @Test fun dealCards_noMoreThan52Cards() {
        SUT.dealCards()
        assertEquals(SUT.myDeck.size + SUT.opponentDeck.size, Card.MAX_CARDS)
    }

    @Test fun dealCards_checkBothDecksHaveSameAmountOfCards() {
        SUT.dealCards()
        assert(SUT.myDeck.size == SUT.opponentDeck.size)
        assertEquals(true, false)
    }

    @Test fun dealCards_checkThereAreNoRepitedCards() {
        SUT.dealCards()

        var cardsRepited = 0
        for (indexCardToCheck in SUT.myDeck.size - 1 downTo 0 step 1) {
            val cardToCheck = SUT.myDeck[indexCardToCheck]
            for (myCardsIndex in SUT.myDeck.size - 1 downTo 0 step 1) {
                if (cardToCheck.number == SUT.myDeck[myCardsIndex].number && cardToCheck.type == SUT.myDeck[myCardsIndex].type) {
                    cardsRepited++
                }
            }
            for (opponentCardsIndex in SUT.opponentDeck.size - 1 downTo 0 step 1) {
                if (cardToCheck.number == SUT.opponentDeck[opponentCardsIndex].number && cardToCheck.type == SUT.opponentDeck[opponentCardsIndex].type) {
                    cardsRepited++
                }
            }
        }
        for (indexCardToCheck in SUT.opponentDeck.size - 1 downTo 0 step 1) {
            val cardToCheck = SUT.myDeck[indexCardToCheck]
            for (opponentCardsIndex in SUT.opponentDeck.size - 1 downTo 0 step 1) {
                if (cardToCheck.number == SUT.opponentDeck[opponentCardsIndex].number && cardToCheck.type == SUT.opponentDeck[opponentCardsIndex].type) {
                    cardsRepited++
                }
            }
        }
        assertEquals(cardsRepited, true)
    }
}