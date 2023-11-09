package com.example.enjoeielo7.models.repository

enum class RepositoryVisibility(val value: String) {
    PUBLIC(value = "public"),
    PRIVATE(value = "private");

    companion object {
        fun getVisibility(visibility: String): RepositoryVisibility =
            if (visibility == "public") PUBLIC else PRIVATE
    }
}