package com.example.enjoeielo7.repository

import com.example.enjoeielo7.network.UserService
import com.example.enjoeielo7.network.response.repository.RepositoryListResponseItem

class UserRepositoryImpl(private val networking: UserService) :
    IUserRepository {
    override suspend fun getUserRepositories(authorization: String): Result<List<RepositoryListResponseItem>> {
        return try {
            Result.success(
                networking.getRepositoryList(
                    "Bearer $authorization"
                )
            )
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}