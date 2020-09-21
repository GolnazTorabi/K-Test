package com.book.store.stock.karafs.ui.relations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.karafs.data.DB.UserDao
import com.book.store.stock.karafs.data.net.BaseResponse
import com.book.store.stock.karafs.data.repository.UserRepository
import javax.inject.Inject


class RelationViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userDao: UserDao
) :
    ViewModel() {
    val userStatus = MutableLiveData<UserStatus>()
    val relations = MutableLiveData<java.lang.StringBuilder>()
    val error = MutableLiveData<String>()
    private val stringBuilder: StringBuilder? = null

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
        for (i in 0..userDao.getAll().size) {
            relations.value = stringBuilder?.append(userDao.getAll()[i])
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