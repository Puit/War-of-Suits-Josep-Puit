package com.nunnos.warofsuitsjoseppuit.data.oldgame

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameConstants.Companion.TABLE_NAME

@Dao
interface OldGameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(oldGame: OldGameEntity)

    @Update
    fun update(oldGame: OldGameEntity)

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY Id DESC")
    fun getAll(): LiveData<List<OldGameEntity>>

    @Query("DELETE FROM " + TABLE_NAME + " WHERE Id = :id")
    fun deleteById(id: Int)

}