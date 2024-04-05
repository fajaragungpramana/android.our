package com.github.fajaragungpramana.our.core.data.remote.auth

import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.data.local.CacheManager
import com.github.fajaragungpramana.our.core.data.remote.auth.request.LoginRequest
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.data.remote.auth.response.LoginResponse
import com.github.fajaragungpramana.our.core.data.remote.auth.response.RegisterResponse
import com.github.fajaragungpramana.our.core.extension.connection
import com.github.fajaragungpramana.our.core.extension.toErrorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val cacheManager: CacheManager
) : IAuthRepository {

    override suspend fun isLogin(): Boolean =
        !cacheManager.get(CacheManager.ACCESS_TOKEN).first().isNullOrEmpty()

    override suspend fun register(registerRequest: RegisterRequest): Flow<AppResult<RegisterResponse>> =
        connection {
            val response = authService.register(registerRequest)
            if (response.isSuccessful)
                AppResult.Success(response.body())
            else {
                val body = response.toErrorBody<RegisterResponse>()
                AppResult.Error(body.message.orEmpty(), response.code())
            }
        }

    override suspend fun login(loginRequest: LoginRequest): Flow<AppResult<LoginResponse>> =
        connection {
            val response = authService.login(loginRequest)
            if (response.isSuccessful) {
                val body = response.body()
                cacheManager.save(CacheManager.ACCESS_TOKEN, body?.loginResult?.token.orEmpty())
                AppResult.Success(body)
            } else {
                val body = response.toErrorBody<LoginResponse>()
                AppResult.Error(body.message.orEmpty(), response.code())
            }
        }

}