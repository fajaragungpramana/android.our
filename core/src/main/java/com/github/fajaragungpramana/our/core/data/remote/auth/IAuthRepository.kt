package com.github.fajaragungpramana.our.core.data.remote.auth

import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.data.remote.auth.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    suspend fun register(registerRequest: RegisterRequest): Flow<AppResult<RegisterResponse>>

}