package com.github.fajaragungpramana.our.core.data.remote.auth.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("password")
    val password: String? = null

)