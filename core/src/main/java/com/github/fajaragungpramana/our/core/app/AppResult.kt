package com.github.fajaragungpramana.our.core.app

sealed class AppResult<T> {

    data class State<T>(val state: AppResultState) : AppResult<T>()
    data class Success<T>(val data: T?) : AppResult<T>()
    data class Error<T>(val message: String, val code: Int) : AppResult<T>()

}