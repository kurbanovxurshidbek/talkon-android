package com.talkon.talkon.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.talkon.talkon.model.Keyword

@Dao
interface KeywordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveKeyword(keyword: Keyword)

    @Query("SELECT * FROM keyword_table")
    fun getKeywords(): List<Keyword>

    @Query("SELECT * FROM keyword_table WHERE keyword LIKE '%' || :keyword || '%'")
    fun searchKeywords(keyword: String): List<Keyword>

    @Query("DELETE FROM keyword_table WHERE id = :id")
    fun deleteKeyword(id: Int)
}