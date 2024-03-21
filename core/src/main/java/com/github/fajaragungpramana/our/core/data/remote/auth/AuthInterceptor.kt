package com.github.fajaragungpramana.our.core.data.remote.auth

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()



        return chain.proceed(requestBuilder.build())
    }

}