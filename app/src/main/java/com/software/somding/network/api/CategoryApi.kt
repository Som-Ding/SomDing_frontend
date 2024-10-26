package com.software.somding.network.api

import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CategoryApi {
	@GET("/api/projects")
	@Headers("Content-Type: application/json")
	fun getProjectsByCategory(
		@Query("category") category: String,
		@Query("sort") sort: String
	): Call<CommonResponse<CategoryProjectResponse>>
}
