package com.book.store.stock.karafs.utility

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

open class AppSharedPreferences @Inject constructor(context: Context) :
    SharedPreferencesHelper {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("shared preferences", 0)

    override  fun setAuthToken(authToken: String?) {
        sharedPreferences.edit()
            .putString("token", "Bearer $authToken")
            .apply()


    }

    override fun getAuthToken(): String {
        return sharedPreferences.getString("token", "")!!
    }
}