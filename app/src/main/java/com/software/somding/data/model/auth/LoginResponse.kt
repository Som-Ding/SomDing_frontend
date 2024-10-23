package com.software.somding.data.model.auth

data class LoginResponse(
	val accessToken: String,
	val refreshToken: String
)
