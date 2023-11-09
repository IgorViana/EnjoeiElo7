package com.example.enjoeielo7.repository

import com.example.enjoeielo7.network.response.repository.RepositoryListResponseItem

interface IUserRepository {
    suspend fun getUserRepositories(
        authorization: String
    ): Result<List<RepositoryListResponseItem>>
}