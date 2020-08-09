package com.ecommerce.data.network

/**
 * Created by Chetan on 09/08/20.
 */
data class APIStatus<out T>(
    val apiResponse: T?,
    val status: Status
) {
    companion object {
        fun <T> loading(apiResponse: T?) = APIStatus(apiResponse, Status.LOADING)

        fun <T> success(apiResponse: T) = APIStatus(apiResponse, Status.SUCCESS)

        fun <T> failure(apiResponse: T?) = APIStatus(apiResponse, Status.FAILURE)
    }
}

enum class Status {
    LOADING,
    SUCCESS,
    FAILURE
}