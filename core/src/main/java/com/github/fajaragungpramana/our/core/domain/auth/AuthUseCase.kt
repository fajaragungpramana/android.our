package com.github.fajaragungpramana.our.core.domain.auth

import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.data.remote.auth.request.LoginRequest
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.domain.auth.model.Login
import com.github.fajaragungpramana.our.core.domain.auth.model.Register
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    suspend fun register(registerRequest: RegisterRequest): Flow<AppResult<Register>>

    suspend fun login(loginRequest: LoginRequest): Flow<AppResult<Login>>

}