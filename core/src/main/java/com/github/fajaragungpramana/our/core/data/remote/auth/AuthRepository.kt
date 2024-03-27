package com.github.fajaragungpramana.our.core.data.remote.auth

import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.data.remote.auth.request.LoginRequest
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.data.remote.auth.response.LoginResponse
import com.github.fajaragungpramana.our.core.data.remote.auth.response.RegisterResponse
import com.github.fajaragungpramana.our.core.extension.connection
import com.github.fajaragungpramana.our.core.extension.toErrorBody
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService) : IAuthRepository {

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

    override suspend fun login(loginRequest: LoginRequest): Flow<AppResult<LoginResponse>> = connection {
        val response = authService.login(loginRequest)
        if (response.isSuccessful)
            AppResult.Success(response.body())
        else {
            val body = response.toErrorBody<LoginResponse>()
            AppResult.Error(body.message.orEmpty(), response.code())
        }
    }

}