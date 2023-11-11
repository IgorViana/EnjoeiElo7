package com.example.enjoeielo7.repository

import androidx.paging.PagingData
import com.example.enjoeielo7.models.repository.RepositoryDetailModel
import com.example.enjoeielo7.models.repository.RepositoryItemModel
import com.example.enjoeielo7.network.response.repository.RepositoryCollaboratorResponse
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUserRepositories(
        authorization: String
    ): Result<List<RepositoryItemModel>>

    suspend fun getPagedUserRepositories(
        authorization: String
    ): Flow<PagingData<RepositoryItemModel>>


    suspend fun getRepositoryDetail(
        authorization: String,
        owner: String,
        repositoryName: String,
    ): Result<RepositoryDetailModel>

    suspend fun getRepositoryReadMe(
        authorization: String,
        owner: String,
        repositoryName: String,
        branch: String,
    ): String?

    suspend fun getRepositoryCollaborators(
        authorization: String,
        owner: String,
        repositoryName: String,
    ): RepositoryCollaboratorResponse?
}