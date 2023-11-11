package com.example.enjoeielo7.network.response.repository

import com.google.gson.annotations.SerializedName

data class RepositoryItemResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("visibility")
    val visibility: String?,
    @SerializedName("owner")
    val owner: OwnerResponse?,
    var collaboratorsUrl: List<RepositoryCollaboratorResponse> = emptyList(),
)