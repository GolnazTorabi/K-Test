package com.book.store.stock.karafs.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.book.store.stock.karafs.data.DB.ResponseUserItem
import com.book.store.stock.karafs.data.DB.UserDao
import com.book.store.stock.karafs.data.net.ApiInterface
import com.book.store.stock.karafs.data.net.BaseResponse
import com.book.store.stock.karafs.data.net.response.ResponseUser
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
    override fun getUsers(): LiveData<BaseResponse<List<ResponseUserItem>>> {
        val allUsers = MutableLiveData<BaseResponse<List<ResponseUserItem>>>()
        val usersData = userDao.getAll()
        if (usersData.isEmpty()) {
            apiInterface.getUsers().enqueue(object : Callback<ResponseUser> {
                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    allUsers.value = ErrorUtils.parseError()
                }

                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful) {
                        allUsers.value = BaseResponse.success(response.body())
                        addToDataBase(response.body())
                    } else {
                        allUsers.value = ErrorUtils.parseError(response)
                    }
                }

            })

        } else {
            allUsers.value = BaseResponse.success(usersData)
        }

        return allUsers
    }

    private fun addToDataBase(response: ResponseUser?) {
        for (i in 0 until response?.size!!) {
            if (response[i].lastName.contains("-")) {
                val firstUser =
                    ResponseUserItem(
                        null,
                        response[i].firstName,
                        response[i].lastName.substringBefore("-")
                    )
                userDao.insert(firstUser)
                val secondUser =
                    ResponseUserItem(
                        null,
                        response[i].firstName,
                        response[i].lastName.substringAfter("-")
                    )
                userDao.insert(secondUser)
            } else {
                userDao.insert(response[i])
            }
        }
    }
}