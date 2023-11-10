package com.example.enjoeielo7.repository

import com.example.enjoeielo7.models.mapper.Mapper
import com.example.enjoeielo7.models.repository.RepositoryDetailModel
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

    override suspend fun getRepositoryDetail(
        authorization: String,
        owner: String,
        repositoryName: String
    ): Result<RepositoryDetailModel> {
        return try {
            val repositoryResponse = networking.getRepositoryDetail(
                authorization = "Bearer $authorization",
                owner = owner,
                repositoryName = repositoryName
            )
            val repositoryModel = Mapper().mapRepositoryDetailResponseToModel(repositoryResponse)
            Result.success(repositoryModel)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}