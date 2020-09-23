package com.test.karafs.ui.relations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.karafs.data.DB.ResponseUserItem
import com.test.karafs.data.DB.UserDao
import com.test.karafs.data.net.BaseResponse
import com.test.karafs.data.repository.UserRepository
import javax.inject.Inject


class RelationViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userDao: UserDao
) :
    ViewModel() {
    val userStatus = MutableLiveData<UserStatus>()
    val relations = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    private var result = ""


    fun getUser() {
        userStatus.value = UserStatus.ShowLoading
        userRepository.getUsers().observeForever {
            userStatus.value = UserStatus.HideLoading
            when (it.status) {
                BaseResponse.Status.Success -> showRelations()
                BaseResponse.Status.BadRequest -> error.value = it.message
                BaseResponse.Status.ServerError -> UserStatus.ServerError
                BaseResponse.Status.Unauthorized -> UserStatus.UnAuthorized
                BaseResponse.Status.NoInternet -> UserStatus.NoInternet
            }
        }
    }


    private fun showRelations() {
        getRelations()
        relations.value = result
    }

    private fun getRelations() {
        for (i in userDao.getAll().indices) {
            var relatedUsers = " "
            val currentUser: ResponseUserItem = userDao.getAll()[i]
            for (j in userDao.getAll().indices) {
                if (i == j) {
                    continue
                }
                val other: ResponseUserItem = userDao.getAll()[j]
                val otherUserLastName: Array<String> = other.lastName.split("-").toTypedArray()
                for (k in otherUserLastName.indices) {
                    if (otherUserLastName[k]
                            .contains(currentUser.lastName) || currentUser.lastName
                            .contains(otherUserLastName[k])
                    ) {
                        relatedUsers += other.firstName + ", "
                    }
                }
            }
            if (relatedUsers.isEmpty()) {
                relatedUsers += "no one"
            } else {
                relatedUsers = relatedUsers.substring(0, relatedUsers.length - 2)
                var reverse = StringBuffer(relatedUsers).reverse().toString()
                reverse = reverse.replaceFirst(",".toRegex(), "& ")
                relatedUsers = StringBuffer(reverse).reverse().toString()
            }
            result += currentUser.firstName + " is related to " + relatedUsers + "\n"
        }

    }


    enum class UserStatus {
        NoInternet,
        UnAuthorized,
        ServerError,
        ShowLoading,
        HideLoading
    }
}