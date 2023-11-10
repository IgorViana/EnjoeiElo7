package com.example.enjoeielo7.network.response.repository

import com.google.gson.annotations.SerializedName

data class RepositoryDetailResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("visibility")
    val visibility: String?,
    @SerializedName("forks_count")
    val forksCount: Long = 0,
    @SerializedName("stargazers_count")
    val starsCount: Long = 0,
)