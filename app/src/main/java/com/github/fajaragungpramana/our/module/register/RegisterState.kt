package com.github.fajaragungpramana.our.module.register

sealed class RegisterState {

    data class OnLoadingRegister(val isLoading: Boolean) : RegisterState()

    data object OnSuccessRegister : RegisterState()

    data class OnMessage(val message: String) : RegisterState()

}