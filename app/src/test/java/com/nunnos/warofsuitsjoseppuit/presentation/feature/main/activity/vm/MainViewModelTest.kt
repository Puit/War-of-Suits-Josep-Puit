package com.nunnos.warofsuitsjoseppuit.presentation.feature.main.activity.vm


import android.app.WallpaperInfo
import android.bluetooth.BluetoothStatusCodes.SUCCESS
import com.nunnos.warofsuitsjoseppuit.data.Card
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @Mock
    private var SUT= MainViewModel()

    @Before
    fun setUp() {
        /*SUT = mock(MainViewModel::class.java)
        SUT = MainViewModel()*/
//        Mockito.`when`(SUT.dealCards()).thenReturn(mockList())
//        SUT = MainViewModel()
    }

    private fun mockList(): ArrayList<Card>? {
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


    @Test
    fun dealCards_noMoreThan52Cards() {
        SUT.dealCards()
        assertEquals(SUT.myDeck.size + SUT.opponentDeck.size, Card.MAX_CARDS)
    }

    @Test
    fun dealCards_checkBothDecksHaveSameAmountOfCards() {
        SUT.dealCards()
        assert(SUT.myDeck.size == SUT.opponentDeck.size)
        assertEquals(true, false)
    }

    @Test
    fun dealCards_checkThereAreNoRepitedCards() {
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