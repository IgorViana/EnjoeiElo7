package com.example.enjoeielo7.repository

import com.example.enjoeielo7.models.repository.RepositoryDetailModel
import com.example.enjoeielo7.models.repository.RepositoryItemModel

interface IUserRepository {
    suspend fun getUserRepositories(
        authorization: String
    ): Result<List<RepositoryItemModel>>


    suspend fun getRepositoryDetail(
        authorization: String,
        owner: String,
        repositoryName: String,
    ): Result<RepositoryDetailModel>
}