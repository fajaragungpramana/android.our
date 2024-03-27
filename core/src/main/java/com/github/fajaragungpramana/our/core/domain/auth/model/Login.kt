package com.github.fajaragungpramana.our.core.domain.auth.model

import com.github.fajaragungpramana.our.core.data.remote.auth.response.LoginResponse

data class Login(
    var token: String? = null
) {

    companion object {

        fun mapToObject(loginResponse: LoginResponse?): Login = Login(
            token = loginResponse?.loginResult?.token
        )

    }

}