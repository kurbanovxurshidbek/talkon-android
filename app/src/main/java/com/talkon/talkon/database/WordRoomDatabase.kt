package com.talkon.talkon.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.talkon.talkon.model.Word
import java.util.concurrent.Executors

@Database(entities = [Word::class], version = 1, exportSchema = false)
internal abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(
            NUMBER_OF_THREADS
        )

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            WordRoomDatabase::class.java, "word_database"
                        )
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        /**
         * Override the onCreate method to populate the database.
         * For this sample, we clear the database every time it is created.
         */
        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                databaseWriteExecutor.execute {

                    // Populate the database in the background.
                    // If you want to start with more words, just add them.
                    val dao: WordDao = INSTANCE!!.wordDao()
                    dao.deleteAll()
                    var word: Word? = Word("Hello")
                    dao.insert(word)
                    word = Word("World")
                    dao.insert(word)
                }
            }
        }
    }
}
