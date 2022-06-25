package com.talkon.talkon.database

import android.app.Application
import com.talkon.talkon.model.Keyword
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KeywordRepository {
    var keywordDao:  KeywordDao

    constructor(application: Application){
        val db = RoomManager.getDatabase(application)
        keywordDao = db.wordDao()
    }

    fun getKeywords(): List<Keyword> {
        return keywordDao.getKeywords()
    }

    fun saveKeyword(keyword: Keyword) {
        keywordDao.saveKeyword(keyword)
    }

    fun deleteKeyword(id: Int){
        keywordDao.deleteKeyword(id)
    }

    fun searchDatabase(word: String): List<Keyword> {
        return keywordDao.searchDatabase(word)
    }
}