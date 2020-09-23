package com.book.store.stock.karafs.data.net

import com.book.store.stock.karafs.data.net.response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/android-test/")
    fun getUsers(): Call<ResponseUser>
}