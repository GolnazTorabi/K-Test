package com.book.store.stock.karafs.utility

interface SharedPreferencesHelper {
    fun setAuthToken(authToken: String?)
    fun getAuthToken(): String
}
