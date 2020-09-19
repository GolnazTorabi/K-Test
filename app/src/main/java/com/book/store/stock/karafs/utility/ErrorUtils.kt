package com.book.store.stock.karafs.utility

import com.book.store.stock.karafs.data.BaseResponse
import org.json.JSONObject
import retrofit2.Response

object ErrorUtils {
    fun <T> parseError(response: Response<T>): BaseResponse<T> {
        try {
            return if (response.code() == 400 || (response.code() in 402..499)) {
                var jsonObject = JSONObject(response.errorBody()!!.string())
                jsonObject = jsonObject.getJSONObject("errors")
                val keys = jsonObject.keys()
                val str_Name = keys.next()
                BaseResponse.badRequest(jsonObject.getJSONArray(str_Name)[0].toString())
            } else if (response.code() == 401) {
                BaseResponse.unauthorized("Unauthorized")
            } else if (response.code() == 500) {
                BaseResponse.serverError("Server Error")
            } else {
                BaseResponse.badRequest("خطای غیر منتظره - لطفا مجددا تلاش کنید.")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return BaseResponse.badRequest("خطای غیر منتظره - لطفا مجددا تلاش کنید.")
        }
    }

    fun <T> parseError(): BaseResponse<T> {
        return try {
            BaseResponse.noInternet("Network Connection Error")
        } catch (ex: Exception) {
            ex.printStackTrace()
            BaseResponse.badRequest("خطای غیر منتظره - لطفا مجددا تلاش کنید.")
        }
    }
}