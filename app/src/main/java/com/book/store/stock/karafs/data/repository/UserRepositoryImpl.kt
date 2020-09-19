package com.book.store.stock.karafs.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.book.store.stock.karafs.data.ApiInterface
import com.book.store.stock.karafs.data.BaseResponse
import com.book.store.stock.karafs.data.DB.UserDao
import com.book.store.stock.karafs.data.response.ResponseUser
import com.book.store.stock.karafs.utility.ErrorUtils
import com.book.store.stock.karafs.utility.SharedPreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private var appSharedPreferences: SharedPreferencesHelper,
    private var userDao: UserDao,
    private var apiInterface: ApiInterface
) : UserRepository {
    override fun getUsers(): LiveData<BaseResponse<ResponseUser>> {
        val allUsers = MutableLiveData<BaseResponse<ResponseUser>>()
        apiInterface.getUsers().enqueue(object : Callback<ResponseUser> {
            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                allUsers.value = ErrorUtils.parseError()
            }

            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    allUsers.value = BaseResponse.success(response.body())
                } else {
                    allUsers.value = ErrorUtils.parseError(response)
                }
            }

        })
        return allUsers
    }
}