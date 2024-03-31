package com.github.fajaragungpramana.our.core.data.remote.auth

import com.github.fajaragungpramana.our.core.data.local.CacheManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val cacheManager: CacheManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        CoroutineScope(Dispatchers.IO).launch {

            val accessToken = cacheManager.get(CacheManager.ACCESS_TOKEN).first()
            if (!accessToken.isNullOrEmpty())
                requestBuilder.addHeader("Authorization", "Bearer $accessToken")

        }

        return chain.proceed(requestBuilder.build())
    }

}