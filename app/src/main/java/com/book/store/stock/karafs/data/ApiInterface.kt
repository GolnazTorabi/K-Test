package com.book.store.stock.karafs.data

import com.book.store.stock.karafs.data.response.ResponseUser
import retrofit2.http.GET

interface ApiInterface {
    companion object {
        const val base_url = "http://karafsapp.com/"
    }

    @GET("android-test")
    fun getUsers(): retrofit2.Call<ResponseUser>
}