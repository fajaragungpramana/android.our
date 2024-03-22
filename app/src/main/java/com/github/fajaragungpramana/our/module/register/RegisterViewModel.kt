package com.github.fajaragungpramana.our.module.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.our.core.app.AppResult
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
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
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _state by lazy { Channel<RegisterState>() }
    val state: Flow<RegisterState>
        get() = _state.receiveAsFlow()

    fun setEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnRegister -> onRegister(event.request)
        }
    }

    private fun onRegister(registerRequest: RegisterRequest): Job = viewModelScope.launch {
        _state.send(RegisterState.OnLoadingRegister(true))
        authUseCase.register(registerRequest).collectLatest {
            when (it) {
                is AppResult.State -> _state.send(RegisterState.OnResultState(it.state))
                is AppResult.Success -> {
                    _state.send(RegisterState.OnLoadingRegister(false))

                    _state.send(RegisterState.OnSuccessRegister)
                }
                is AppResult.Error -> _state.send(RegisterState.OnMessage(it.message))
            }
        }
    }

}