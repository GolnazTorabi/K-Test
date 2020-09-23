package com.test.karafs.data.DB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ResponseUserItem::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

}
