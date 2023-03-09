package com.nunnos.warofsuitsjoseppuit.data.oldgame

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameConstants.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class OldGameEntity {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "result")
    var result: String = ""

    @ColumnInfo(name = "suits")
    var suits: String = ""

    @ColumnInfo(name = "date")
    var date: String = ""

    @ColumnInfo(name = "time")
    var time: String = ""

    @ColumnInfo(name = "game")
    var game: String = ""

    constructor(
        id: Int,
        result: String,
        suits: String,
        date: String,
        time: String,
        game: String
    ) {
        this.id = id
        this.result = result
        this.suits = suits
        this.date = date
        this.time = time
        this.game = game
    }

    constructor(
        result: String,
        suits: String,
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
}