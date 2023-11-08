package com.example.enjoeielo7.util

sealed class Result<out T>(
) {
    data class Success<out T>(val response: T) : Result<T>()
    data class Error(val error: String) : Result<Nothing>()

    fun isSuccess(): Boolean = this is Success
    fun isFailure(): Boolean = this is Error

    inline fun onSuccess(action: (T) -> Unit ): Result<T> {
        if (this is Success) {
            action(response)
        }
        return this
    }

    inline fun onFailure(action: (String) -> Unit): Result<T> {
        if (this is Error) {
            action(error)
        }
        return this
    }
}