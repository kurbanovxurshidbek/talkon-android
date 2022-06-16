package com.talkon.talkon.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.talkon.talkon.model.Word

internal class WordRepository(application: Application?) {
    private val mWordDao: WordDao
    private val mAllWords: LiveData<List<Word>>

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>>
        get() = mAllWords

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(word: Word) {
        WordRoomDatabase.databaseWriteExecutor.execute { mWordDao.insert(word) }
    }

    fun getAll() {
        WordRoomDatabase.databaseWriteExecutor.execute { mWordDao.getAll() }
    }

    fun getWord(id: Int) {
//        WordRoomDatabase.databaseWriteExecutor.execute { mWordDao.getWord(id) }
    }

    fun delete(id: Int) {
        WordRoomDatabase.databaseWriteExecutor.execute { mWordDao.delete(id) }
    }

    fun deleteAll() {
        WordRoomDatabase.databaseWriteExecutor.execute { mWordDao.deleteAll() }
    }

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    init {
        val db = WordRoomDatabase.getDatabase(application!!)
        mWordDao = db!!.wordDao()
        mAllWords = mWordDao.getAlphabetizedWords
    }
}
