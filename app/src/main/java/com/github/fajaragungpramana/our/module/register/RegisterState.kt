package com.github.fajaragungpramana.our.module.register

import com.github.fajaragungpramana.our.core.app.AppResultState

sealed class RegisterState {

    data class OnResultState(val state: AppResultState) : RegisterState()

    data class OnLoadingRegister(val isLoading: Boolean) : RegisterState()

    data object OnSuccessRegister : RegisterState()

    data class OnMessage(val message: String) : RegisterState()

}