package com.example.enjoeielo7.repository

import com.example.enjoeielo7.models.mapper.Mapper
import com.example.enjoeielo7.models.repository.RepositoryItemModel
import com.example.enjoeielo7.network.UserService

class UserRepositoryImpl(private val networking: UserService) :
    IUserRepository {
    override suspend fun getUserRepositories(authorization: String): Result<List<RepositoryItemModel>> {
        return try {
            val repositoryResponse = networking.getRepositoryList("Bearer $authorization")
            val repositoryModel = Mapper().mapRepositoryListResponseToModel(repositoryResponse)
            Result.success(repositoryModel)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}