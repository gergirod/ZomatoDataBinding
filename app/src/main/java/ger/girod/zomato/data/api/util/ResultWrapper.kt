package ger.girod.zomato.data.api.util

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class ServerError(val errorMessage: String) : ResultWrapper<Nothing>()
}