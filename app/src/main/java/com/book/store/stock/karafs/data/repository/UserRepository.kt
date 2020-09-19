package com.book.store.stock.karafs.data.repository

import androidx.lifecycle.LiveData
import com.book.store.stock.karafs.data.BaseResponse
import com.book.store.stock.karafs.data.response.ResponseUser

interface UserRepository {
    fun getUsers(): LiveData<BaseResponse<ResponseUser>>
}