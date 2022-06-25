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
    var searchedKeywords = MutableLiveData<List<Keyword>>()

    fun searchKeyword(keyword: String) {
        if (keyword.isNotEmpty()){
            var list = searchedKeywords.value!!.toMutableList()
            with(list){
                clear()
            }

            Log.d("@@", searchedKeywords.value.toString())
            for (item in allKeywords.value!!) {
                if (item.keyword.startsWith(keyword)){
                    var list = searchedKeywords.value!!.toMutableList()
                    with(list) {
                        add(item)
                    }
                }
            }
        } else{
            searchedKeywords = allKeywords
        }
    }

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

    fun searchDatabase(repository: KeywordRepository, keyword: String): LiveData<List<Keyword>> {
        allKeywords.value = repository.searchDatabase(keyword)
        return allKeywords
    }

}