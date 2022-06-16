package com.talkon.talkon.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.talkon.talkon.model.Word

/**
 * The Room Magic is in this file, where you map a Java method call to an SQL query.
 *
 * When you are using complex data types, such as Date, you have to also supply type converters.
 * To keep this example basic, no types that require type converters are used.
 * See the documentation at
 * https://developer.android.com/topic/libraries/architecture/room.html#type-converters
 */
@Dao
interface WordDao {
    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @get:Query("SELECT * FROM word_table ORDER BY word ASC")
    val getAlphabetizedWords: LiveData<List<Word>>

    @Query("select * from word_table")
    fun getAll(): List<Word>

    @Query("SELECT * FROM word_table WHERE id = :id")
    fun getWord(id: Int):LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word?)

    @Query("DELETE FROM word_table WHERE id = :id")
    fun delete(id: Int)

    @Query("DELETE FROM word_table")
    fun deleteAll()
}