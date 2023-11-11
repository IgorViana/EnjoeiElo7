package com.example.enjoeielo7.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.enjoeielo7.models.mapper.Mapper
import com.example.enjoeielo7.models.repository.RepositoryDetailModel
import com.example.enjoeielo7.models.repository.RepositoryItemModel
import com.example.enjoeielo7.network.UserService
import com.example.enjoeielo7.network.response.repository.RepositoryCollaboratorResponse
import com.example.enjoeielo7.paging.RepositoryPagingSource
import kotlinx.coroutines.flow.Flow

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

    override suspend fun getPagedUserRepositories(authorization: String): Flow<PagingData<RepositoryItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                RepositoryPagingSource(
                    network = networking,
                    authorization = "Bearer $authorization"
                )
            }
        ).flow
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
            val readMe = getRepositoryReadMe(
                authorization = authorization,
                owner = owner,
                repositoryName = repositoryName,
                branch = repositoryResponse.defaultBranch.orEmpty()
            )
            val repositoryModel =
                Mapper().mapRepositoryDetailResponseToModel(repositoryResponse, readMe)
            Result.success(repositoryModel)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    override suspend fun getRepositoryReadMe(
        authorization: String,
        owner: String,
        repositoryName: String,
        branch: String
    ): String? {
        return try {
            networking.getRepositoryReadMe(
                authorization = "Bearer $authorization",
                owner = owner,
                repositoryName = repositoryName,
                branch = branch
            )
        } catch (ex: Exception) {
            null
        }
    }

    override suspend fun getRepositoryCollaborators(
        authorization: String,
        owner: String,
        repositoryName: String
    ): List<RepositoryCollaboratorResponse?> {
        return try {
            networking.getRepositoryCollaborators(
                authorization = "Bearer $authorization",
                owner = owner,
                repositoryName = repositoryName
            )
        } catch (ex: Exception) {
            emptyList<RepositoryCollaboratorResponse>()
        }
    }
}