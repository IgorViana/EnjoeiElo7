package com.example.enjoeielo7.network

import com.example.enjoeielo7.network.response.repository.RepositoryItemResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface UserService {

    @Headers("Accept: application/vnd.github+json")
    @GET("https://api.github.com/user/repos?visibility=all&per_page=100")
    suspend fun getRepositoryList(
        @Header("Authorization") authorization: String
    ): List<RepositoryItemResponse>
}