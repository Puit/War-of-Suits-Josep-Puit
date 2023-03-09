package com.nunnos.warofsuitsjoseppuit.data.oldgame

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameConstants.Companion.LATEST_VERSION
import com.nunnos.warofsuitsjoseppuit.data.oldgame.OldGameConstants.Companion.TABLE_NAME

@Database(
    entities = [OldGameEntity::class],
    version = LATEST_VERSION,
    exportSchema = true
)
abstract class OldGameDB : RoomDatabase() {
    abstract fun getDao(): OldGameDao

    companion object {
        @Volatile
        private var instance: OldGameDB? = null

        @Synchronized
        fun getInstance(context: Context): OldGameDB {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OldGameDB::class.java,
                    TABLE_NAME
                ).build()
                this.instance = instance
                return instance
            }
        }
    }
}