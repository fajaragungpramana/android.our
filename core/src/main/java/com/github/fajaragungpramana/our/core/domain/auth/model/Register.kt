package com.github.fajaragungpramana.our.core.domain.auth.model

import com.github.fajaragungpramana.our.core.data.remote.auth.response.RegisterResponse

data class Register(
    var message: String? = null
) {

    companion object {

        fun mapToObject(registerResponse: RegisterResponse?): Register = Register(
            message = registerResponse?.message
        )

    }

}