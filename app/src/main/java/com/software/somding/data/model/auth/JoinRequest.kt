package com.software.somding.data.model.auth

data class JoinRequest (
	val email: String,
	val password: String,
	val nickname: String
)