package com.book.store.stock.karafs.data.repository

import androidx.lifecycle.LiveData
import com.book.store.stock.karafs.data.DB.ResponseUserItem
import com.book.store.stock.karafs.data.net.BaseResponse
import com.book.store.stock.karafs.data.net.response.ResponseUser

interface UserRepository {
    fun getUsers(): LiveData<BaseResponse<List<ResponseUserItem>>>
}