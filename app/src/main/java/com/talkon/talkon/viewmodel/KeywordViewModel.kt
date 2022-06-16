package com.talkon.talkon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talkon.talkon.database.KeywordDao
import com.talkon.talkon.database.KeywordRepository
import com.talkon.talkon.model.Keyword

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */
class KeywordViewModel() : ViewModel() {
    val allKeywords = MutableLiveData<List<Keyword>>()

    fun saveKeyword(repository: KeywordRepository, keyword: Keyword) {
        repository.saveKeyword(keyword)
    }

    fun getKeywords(repository: KeywordRepository) : LiveData<List<Keyword>>{
        allKeywords.value = repository.getKeywords()
        return allKeywords
    }

    fun deleteKeyword(repository: KeywordRepository, id: Int){
        repository.deleteKeyword(id)
    }
}