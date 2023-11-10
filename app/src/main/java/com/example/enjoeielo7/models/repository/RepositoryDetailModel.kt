package com.example.enjoeielo7.models.repository

data class RepositoryDetailModel(
    val name: String?,
    val fullName: String?,
    val description: String?,
    val visibility: RepositoryVisibility,
    val forksCount: Long = 0,
    val starsCount: Long = 0,
    val readMe: String? = null,
    val defaultBranch: String?,
)