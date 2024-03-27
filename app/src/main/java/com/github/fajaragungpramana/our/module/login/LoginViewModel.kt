package com.github.fajaragungpramana.our.module.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.data.remote.auth.request.LoginRequest
import com.github.fajaragungpramana.our.core.domain.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _state by lazy { Channel<LoginState>() }
    val state: Flow<LoginState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> onLogin(event.loginRequest)
        }
    }

    private fun onLogin(loginRequest: LoginRequest): Job = viewModelScope.launch {
        _state.send(LoginState.OnLoadingLogin(isLoading = true))

        authUseCase.login(loginRequest).collectLatest {
            _state.send(LoginState.OnLoadingLogin(isLoading = false))

            when (it) {
                is AppResult.Success -> _state.send(LoginState.OnSuccessLogin)
                is AppResult.Error -> _state.send(LoginState.OnMessage(it.message))
            }
        }
    }

}