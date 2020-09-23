package com.book.store.stock.karafs.data.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
data class ResponseUserItem(
    @PrimaryKey
    @Expose
    val id:Int?=null,
    @ColumnInfo(name = "first_name")val firstName: String,
    @ColumnInfo(name = "last_name")val lastName: String
)