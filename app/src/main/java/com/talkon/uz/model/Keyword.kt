package com.talkon.uz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keyword_table")
open class Keyword(@ColumnInfo(name = "keyword") val keyword: String){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}