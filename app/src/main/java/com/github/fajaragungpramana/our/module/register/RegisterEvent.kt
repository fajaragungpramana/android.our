package com.github.fajaragungpramana.our.module.register

import com.github.fajaragungpramana.our.core.data.remote.auth.request.RegisterRequest

sealed class RegisterEvent {

    data class OnRegister(val request: RegisterRequest) : RegisterEvent()

}