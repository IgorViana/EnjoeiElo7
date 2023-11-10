package com.example.enjoeielo7.network.response.repository

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("login")
    val login: String?,
)
