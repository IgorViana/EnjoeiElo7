package com.example.enjoeielo7.network

import com.example.enjoeielo7.network.response.login.ApiTokenResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    suspend fun postApiToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("state") state: String,
    ): ApiTokenResponse

}