package com.test.karafs.data.repository

import androidx.lifecycle.LiveData
import com.test.karafs.data.DB.ResponseUserItem
import com.test.karafs.data.net.BaseResponse

interface UserRepository {
    fun getUsers(): LiveData<BaseResponse<List<ResponseUserItem>>>
}