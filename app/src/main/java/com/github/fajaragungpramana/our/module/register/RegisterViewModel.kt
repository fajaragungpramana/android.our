package com.github.fajaragungpramana.our.module.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest
import com.github.fajaragungpramana.our.core.domain.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
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

    }

}