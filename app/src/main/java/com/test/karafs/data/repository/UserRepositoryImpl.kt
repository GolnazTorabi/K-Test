package com.test.karafs.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.karafs.data.DB.ResponseUserItem
import com.test.karafs.data.DB.UserDao
import com.test.karafs.data.net.ApiInterface
import com.test.karafs.data.net.BaseResponse
import com.test.karafs.data.net.response.ResponseUser
import com.test.karafs.utility.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
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
            userDao.insert(response[i])
        }
    }
}