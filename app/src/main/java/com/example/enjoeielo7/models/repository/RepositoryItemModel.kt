package com.example.enjoeielo7.models.repository

data class RepositoryItemModel(
    val name: String?,
    val description: String?,
    val language: String?,
    val visibility: RepositoryVisibility = RepositoryVisibility.PRIVATE,
    val collaboratorsUrl: List<String?> = emptyList(),
    val owner: String?
)