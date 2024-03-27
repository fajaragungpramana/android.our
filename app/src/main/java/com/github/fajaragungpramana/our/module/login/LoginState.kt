package com.github.fajaragungpramana.our.module.login

sealed class LoginState {

    data class OnLoadingLogin(val isLoading: Boolean) : LoginState()

    data object OnSuccessLogin : LoginState()

    data class OnMessage(val message: String) : LoginState()

}