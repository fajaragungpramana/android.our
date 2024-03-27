package com.github.fajaragungpramana.our.core.domain.auth

import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.data.remote.auth.IAuthRepository
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.domain.auth.model.Register
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: IAuthRepository) :
    AuthUseCase {

    override suspend fun register(registerRequest: RegisterRequest): Flow<AppResult<Register>> =
        channelFlow {
            authRepository.register(registerRequest).collectLatest {
                when (it) {
                    is AppResult.State -> send(AppResult.State(it.state))
                    is AppResult.Success -> send(AppResult.Success(Register.mapToObject(it.data)))
                    is AppResult.Error -> send(AppResult.Error(it.message, it.code))
                }
            }
        }

}