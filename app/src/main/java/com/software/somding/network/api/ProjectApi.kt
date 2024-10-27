package com.software.somding.network.api

import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.project.ProjectDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectApi {
	@GET("api/projects/{projectId}")
	fun getProjectDetail(
		@Path("projectId") projectId: Int
	): Call<ProjectDetailResponse>

	@GET("api/projects/my")
	fun getMyProject(): Call<CategoryProjectResponse>
}
