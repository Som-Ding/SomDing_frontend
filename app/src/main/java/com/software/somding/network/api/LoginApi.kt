package com.software.somding.network.api

import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.data.model.auth.LoginResponse
import com.software.somding.data.model.common.CommonResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
	@POST("api/auth/login")
	fun login(
		@Body loginRequest: LoginRequest
	): Call<CommonResponse<LoginResponse>>
}