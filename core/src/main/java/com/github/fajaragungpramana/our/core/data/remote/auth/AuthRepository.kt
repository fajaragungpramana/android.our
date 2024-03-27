package com.github.fajaragungpramana.our.core.data.remote.auth

import androidx.core.util.PatternsCompat
import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.app.AppResultState
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.data.remote.auth.response.RegisterResponse
import com.github.fajaragungpramana.our.core.extension.connection
import com.github.fajaragungpramana.our.core.extension.toErrorBody
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService) : IAuthRepository {

    override suspend fun register(registerRequest: RegisterRequest): Flow<AppResult<RegisterResponse>> =
        connection {
            when {
                registerRequest.name.orEmpty().length < 4 ->
                    AppResult.State(AppResultState.InvalidName)

                !PatternsCompat.EMAIL_ADDRESS.matcher(registerRequest.email.orEmpty()).matches() ->
                    AppResult.State(AppResultState.InvalidEmail)

                registerRequest.password.orEmpty().length < 8 ->
                    AppResult.State(AppResultState.InvalidPassword)

                else -> {
                    val response = authService.register(registerRequest)
                    if (response.isSuccessful)
                        AppResult.Success(response.body())
                    else {
                        val body = response.toErrorBody<RegisterResponse>()
                        AppResult.Error(body.message.orEmpty(), response.code())
                    }
                }
            }
        }

}