package com.talkon.talkon.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.talkon.talkon.database.WordRepository
import com.talkon.talkon.model.Word

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */
class SearchResultActivityViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mRepository: WordRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private var mAllWords: LiveData<List<Word>>
    val allWords: LiveData<List<Word>>
        get() = mAllWords

    fun insert(word: Word) {
        mRepository.insert(word)
    }

    fun getAll() {
        mRepository.getAll()
    }

    fun getWord(id: Int) {
        mRepository.getWord(id)
    }

    fun performSearch(keyword: String) {
//        if (keyword.isEmpty())
//            mAllWords
//        else
//            mRepository.deleteAll()
//        for (name in allWords.value.size) {
//            if (name!!.toLowerCase().contains(keyword.toLowerCase())) {
//                mRepository.insert(name)
//            }
//        }
//        mAllWords
    }

    fun delete(position: Int) {
        mRepository.delete(position)
        getAll()
    }

    init {
        mRepository = WordRepository(application)
        mAllWords = mRepository.allWords
    }
}