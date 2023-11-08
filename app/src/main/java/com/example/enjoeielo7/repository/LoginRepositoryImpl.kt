package com.example.enjoeielo7.repository

import com.example.enjoeielo7.network.LoginService
import com.example.enjoeielo7.network.response.login.ApiTokenResponse
import com.example.enjoeielo7.util.CLIENT_ID
import com.example.enjoeielo7.util.CLIENT_SECRET

class LoginRepositoryImpl(private val networking: LoginService) : ILoginRepository {
    override suspend fun getToken(
        clientId: String,
        clientSecret: String,
        code: String,
        state: String
    ): Result<ApiTokenResponse> {
        return try {
            Result.success(
                networking.postApiToken(
                    clientId = CLIENT_ID, clientSecret = CLIENT_SECRET, code = code, state = state
                )
            )
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}