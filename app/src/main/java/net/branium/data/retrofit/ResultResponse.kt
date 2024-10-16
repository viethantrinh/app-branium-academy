package net.branium.data.retrofit

abstract class ResultResponse<T> {
    data class Success<T>(val data: T?) : ResultResponse<T>()
    data class Error<T>(val exception: Exception) : ResultResponse<T>()
}