package com.github.fajaragungpramana.our.core.extension

import com.github.fajaragungpramana.our.core.app.AppResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

suspend inline fun <T : Any> connection(crossinline run: suspend () -> AppResult<T>) = channelFlow {
    try {
        send(run())
    } catch (e: Exception) {
        send(AppResult.Error(e.message.orEmpty(), 1))
    }
}.flowOn(Dispatchers.IO)

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Map<String, T?>.removeNulls(): Map<String, T> = filterValues { it != null } as Map<String, T>

inline fun <reified T> Response<T>.toErrorBody(): T = Gson().fromJson(this.errorBody()?.string(), T::class.java)

inline fun <reified T> toJson(): String = Gson().toJson(T::class)