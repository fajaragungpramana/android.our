package com.github.fajaragungpramana.our.core.data.remote.auth.response

import com.google.gson.annotations.SerializedName

data class LoginResultResponse(

    @SerializedName("userId")
    val userId: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("token")
    val token: String? = null

)