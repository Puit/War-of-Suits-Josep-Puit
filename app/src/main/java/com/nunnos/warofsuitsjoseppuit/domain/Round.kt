package com.nunnos.warofsuitsjoseppuit.domain

import com.nunnos.warofsuitsjoseppuit.data.Card

class Round(myCard: Card, oppponentCard: Card, isWon: Boolean) {
    var myCard: Card
    var oppponentCard: Card
    var isWon: Boolean

    init {
        this.myCard = myCard
        this.oppponentCard = oppponentCard
        this.isWon = isWon
    }
}