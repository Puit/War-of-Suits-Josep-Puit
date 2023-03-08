package com.nunnos.warofsuitsjoseppuit.data.oldgame

import com.nunnos.warofsuitsjoseppuit.data.Card

class OldGame(isWon: Boolean, suits: ArrayList<Card.Type>, date: String, time: String, game: String) {
    var isWon: Boolean private set
    var suits: ArrayList<Card.Type> private set
    var date: String private set
    var time: String private set
    var game: String private set

    init {
        this.isWon = isWon
        this.suits = suits
        this.date = date
        this.time = time
        this.game = game
    }
}