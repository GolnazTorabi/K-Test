package com.book.store.stock.karafs.ui.relations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.book.store.stock.karafs.data.BaseResponse
import com.book.store.stock.karafs.data.repository.UserRepository
import com.book.store.stock.karafs.data.response.ResponseUser
import javax.inject.Inject


class RelationViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    val userStatus = MutableLiveData<UserStatus>()
    val relations = MutableLiveData<String>()

    fun getUser() {
        userStatus.value = UserStatus.ShowLoading
        userRepository.getUsers().observeForever {
            userStatus.value = UserStatus.HideLoading
            when (it.status) {
                BaseResponse.Status.Success -> showRelations(it.data)
                BaseResponse.Status.BadRequest -> UserStatus.Fail
                BaseResponse.Status.ServerError -> UserStatus.ServerError
                BaseResponse.Status.Unauthorized -> UserStatus.UnAuthorized
                BaseResponse.Status.NoInternet -> UserStatus.NoInternet
            }
        }
    }

    private fun showRelations(data: ResponseUser?) {
        var stringBuilder: StringBuilder? = null
        for (i in 0..data?.list?.size!!) {

        }
    }


    enum class UserStatus {
        Fail,
        NoInternet,
        UnAuthorized,
        ServerError,
        ShowLoading,
        HideLoading
    }
}