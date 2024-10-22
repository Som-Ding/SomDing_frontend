package com.software.somding.presentation.login.repository

import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.data.model.auth.LoginResponse
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.network.api.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun login(loginRequest: LoginRequest): CommonResponse<LoginResponse> {
        return authApi.login(loginRequest)
    }
}
