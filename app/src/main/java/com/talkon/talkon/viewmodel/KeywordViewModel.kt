package com.talkon.talkon.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import com.talkon.talkon.database.KeywordRepository
import com.talkon.talkon.model.Keyword

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */
class KeywordViewModel : ViewModel() {
    val allKeywords = MutableLiveData<List<Keyword>>()

    fun saveKeyword(repository: KeywordRepository, keyword: Keyword) {
        repository.saveKeyword(keyword)
    }

    fun getKeywords(repository: KeywordRepository): LiveData<List<Keyword>> {
        allKeywords.value = repository.getKeywords()
        return allKeywords
    }

    fun deleteKeyword(repository: KeywordRepository, id: Int) {
        repository.deleteKeyword(id)
    }

    fun searchKeywords(repository: KeywordRepository, keyword: String): LiveData<List<Keyword>> {
        allKeywords.value = repository.searchKeywords(keyword)
        Log.d("TAG", allKeywords.value!!.size.toString())
        return allKeywords
    }
}