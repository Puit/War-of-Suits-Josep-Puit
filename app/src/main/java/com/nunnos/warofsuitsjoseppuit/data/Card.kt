package com.nunnos.warofsuitsjoseppuit.data

class Card(type: Type, number: Number) {
    companion object {
        const val MAX_CARDS = 52
    }
    enum class Type {
        CLUBS,
        DIAMONTS,
        HEARTS,
        SPADES,
    }

    enum class Number {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        J,
        Q,
        K,
        ACE
    }

    var type: Type private set
    var number: Number private set

    init {
        this.type = type
        this.number = number
    }

    @Override
    override fun toString(): String {
        return "(" + type.name + ", " + number.name + ")"
    }
}