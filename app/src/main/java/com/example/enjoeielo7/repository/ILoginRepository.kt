package com.example.enjoeielo7.repository

import com.example.enjoeielo7.network.response.login.ApiTokenResponse

interface ILoginRepository {

    suspend fun getToken(
        clientId: String,
        clientSecret: String,
        code:String,
        state:String
    ): Result<ApiTokenResponse>
}