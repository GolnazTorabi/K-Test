package com.test.karafs.data.net

data class BaseResponse<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): BaseResponse<T> {
            return BaseResponse(
                Status.Success,
                data,
                null
            )
        }

        fun <T> badRequest(msg: String): BaseResponse<T> {
            return BaseResponse(
                Status.BadRequest,
                null,
                msg
            )
        }

        fun <T> noInternet(msg: String): BaseResponse<T> {
            return BaseResponse(
                Status.NoInternet,
                null,
                msg
            )
        }

        fun <T> unauthorized(msg: String): BaseResponse<T> {
            return BaseResponse(
                Status.Unauthorized,
                null,
                msg
            )
        }


        fun <T> serverError(msg: String): BaseResponse<T> {
            return BaseResponse(
                Status.ServerError,
                null,
                msg
            )
        }


    }

    enum class Status {
        Success,
        BadRequest,

        NoInternet,
        Unauthorized,
        ServerError
    }
}