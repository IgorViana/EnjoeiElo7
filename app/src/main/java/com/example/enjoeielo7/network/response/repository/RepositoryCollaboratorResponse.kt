package com.example.enjoeielo7.network.response.repository

import com.google.gson.annotations.SerializedName

data class RepositoryCollaboratorResponse (
    @SerializedName("id")
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)