package com.nunnos.warofsuitsjoseppuit.domain

import com.nunnos.warofsuitsjoseppuit.data.Card

class OldGame {
    var result: String = ""
    var suits: ArrayList<Card.Type> = ArrayList()
    var date: String = ""
    var time: String = ""
    var game: String = ""


    constructor(
        result: String,
        suits: ArrayList<Card.Type>,
        date: String,
        time: String,
        game: String
    ) {
        this.result = result
        this.suits = suits
        this.date = date
        this.time = time
        this.game = game
    }

    constructor()

    fun addGame(round: Round) {
        addGame(round.myCard, round.oppponentCard, round.isWon)
    }

    fun addGame(myCard: Card, opponentCard: Card, isWon: Boolean) {
        this.game =
            this.game + myCard.toString() + opponentCard.toString() + isWon + "/"
    }
}