package com.github.fajaragungpramana.our.module.login

import com.github.fajaragungpramana.our.core.data.remote.auth.request.LoginRequest

sealed class LoginEvent {

    data class OnLogin(val loginRequest: LoginRequest) : LoginEvent()

}