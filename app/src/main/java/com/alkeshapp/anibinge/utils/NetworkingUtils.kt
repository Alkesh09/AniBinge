package com.alkeshapp.anibinge.utils

enum class Status { LOADING, SUCCESS, ERROR }
sealed class FlowResult<out T>{

    data class Success<out T>(val data: T): FlowResult<T>()
    data class Failure(val msg: String?) : FlowResult<Nothing>()
    object Loading: FlowResult<Nothing>()
}

data class FlowResponse<out T>(
    val status: Status,
    val data: T? = null,
    val error: String? = null
) {
    companion object {
        fun <T> loading(): FlowResponse<T> = FlowResponse(Status.LOADING, null, null)

        fun <T> success(data: T?): FlowResponse<T> = FlowResponse(Status.SUCCESS, data, null)

        fun <T> error(error: String?): FlowResponse<T> = FlowResponse(Status.ERROR, null, error)
    }
}