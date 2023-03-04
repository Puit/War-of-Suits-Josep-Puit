package com.nunnos.warofsuits.data

class Card(type: Type, number: Number) {
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
    private lateinit var type:Type
    private lateinit var number: Number
    init {
        this.type = type
        this.number = number
    }
    fun getType(): Type{
        return type
    }
    fun getNumber(): Number{
        return number
    }
}