package com.book.store.stock.karafs.data.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.book.store.stock.karafs.data.response.ResponseUserItem

@Database(entities = [ResponseUserItem::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

}
