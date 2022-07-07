package com.talkon.talkon.database

import android.app.Application
import com.talkon.talkon.model.Keyword

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

    fun searchKeywords(keyword: String): List<Keyword> {
        return keywordDao.searchKeywords(keyword)
    }
}