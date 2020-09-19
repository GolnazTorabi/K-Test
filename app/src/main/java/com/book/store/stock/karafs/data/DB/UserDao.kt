package com.book.store.stock.karafs.data.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.book.store.stock.karafs.data.response.ResponseUserItem

@Dao
interface UserDao {
    @Insert
    fun insert(vararg user: ResponseUserItem?)

    @Query("SELECT * FROM responseuseritem")
    fun getAll(): List<ResponseUserItem>
}