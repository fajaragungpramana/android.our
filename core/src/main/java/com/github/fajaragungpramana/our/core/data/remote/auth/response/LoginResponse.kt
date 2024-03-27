package com.github.fajaragungpramana.our.core.data.remote.auth.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("error")
    val error: Boolean? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("loginResult")
    val loginResult: LoginResultResponse? = null

)