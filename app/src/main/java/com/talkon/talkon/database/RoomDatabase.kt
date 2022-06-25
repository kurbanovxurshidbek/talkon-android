package com.talkon.talkon.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.talkon.talkon.model.Keyword

@Database(
    entities = [Keyword::class],
    version = 2
)
abstract class RoomManager : RoomDatabase() {

    abstract fun wordDao(): KeywordDao
    companion object {
        @Volatile
        private var INSTANCE: RoomManager? = null
        fun getDatabase(context: Context): RoomManager {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomManager::class.java,
                        "talkon_db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

