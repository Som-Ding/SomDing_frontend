package com.software.somding.network

import retrofit2.Retrofit
import javax.inject.Inject

class ApiClient @Inject constructor(
	private val retrofit: Retrofit
) {
	private inline fun <reified T> createService(): T {
		return retrofit.create(T::class.java)
	}
}
