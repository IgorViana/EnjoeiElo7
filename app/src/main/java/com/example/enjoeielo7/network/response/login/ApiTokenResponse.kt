package com.example.enjoeielo7.network.response.login

import com.google.gson.annotations.SerializedName

data class ApiTokenResponse(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("scope")
    val scope: Boolean,
    @SerializedName("token_type")
    val tokenType: Boolean,
)
