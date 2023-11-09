package com.example.enjoeielo7.network.response.repository

import com.google.gson.annotations.SerializedName

data class RepositoryListResponseItem(
    @SerializedName("access_token")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: Any,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String,
)