package com.example.enjoeielo7.network

import com.example.enjoeielo7.network.response.repository.RepositoryCollaboratorResponse
import com.example.enjoeielo7.network.response.repository.RepositoryDetailResponse
import com.example.enjoeielo7.network.response.repository.RepositoryItemResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @Headers("Accept: application/vnd.github+json")
    @GET("https://api.github.com/user/repos?visibility=all&per_page=10")
    suspend fun getRepositoryList(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int = 1,
    ): List<RepositoryItemResponse>


    @Headers("Accept: application/vnd.github+json")
    @GET("https://api.github.com/repos/{owner}/{repo}")
    suspend fun getRepositoryDetail(
        @Header("Authorization") authorization: String,
        @Path("owner") owner: String,
        @Path("repo") repositoryName: String,
    ): RepositoryDetailResponse

    @GET("https://raw.githubusercontent.com/{owner}/{repo}/{branch}/README.md")
    suspend fun getRepositoryReadMe(
        @Header("Authorization") authorization: String,
        @Path("owner") owner: String,
        @Path("repo") repositoryName: String,
        @Path("branch") branch: String,
    ): String?

    @Headers("Accept: application/vnd.github+json")
    @GET("https://api.github.com/repos/{owner}/{repo}/collaborators")
    suspend fun getRepositoryCollaborators(
        @Header("Authorization") authorization: String,
        @Path("owner") owner: String,
        @Path("repo") repositoryName: String,
    ): List<RepositoryCollaboratorResponse>
}