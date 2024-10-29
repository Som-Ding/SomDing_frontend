package com.software.somding.network.api

import com.google.android.gms.common.internal.service.Common
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.project.CreateQuestionRequestDTO
import com.software.somding.data.model.project.ProjectDetailResponse
import com.software.somding.data.model.project.QuestionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectApi {
	@GET("api/projects/{projectId}")
	fun getProjectDetail(
		@Path("projectId") projectId: Int
	): Call<ProjectDetailResponse>

	/*@Multipart
	@POST("/api/projects")
	fun createProject(
		@Body projectReq: ProjectRequest, @Part images: List<MultipartBody.Part>
	): Call<CommonResponse<String>>*/

	@Multipart
	@POST("api/projects")
	@Headers("Content-Type: application/json")
	fun createProject(
		@Part("title") title: RequestBody,
		@Part("introduce") introduce: RequestBody,
		@Part("policy") policy: RequestBody,
		@Part("schedule") schedule: RequestBody,
		@Part("category") category: RequestBody,
		@Part("targetPrice") targetPrice: RequestBody,
		@Part("price") price: RequestBody,
		@Part("targetDate") targetDate: RequestBody,
		@Part("colorList") colorList: RequestBody,
		@Part("sizeList") sizeList: RequestBody,
		@Part("otherList") otherList: RequestBody,
		@Part images: List<MultipartBody.Part>
	): Call<CommonResponse<String>>

	@GET("api/projects/my")
	fun getMyProject(): Call<CategoryProjectResponse>

	@GET("api/projects/questions/{projectId}")
	fun getAllQuestion(
		@Path("projectId") projectId: Int
	): Call<QuestionResponse>

	@POST("api/questions")
	fun createQuestion(
		@Body request: CreateQuestionRequestDTO
	): Call<CommonResponse<String>>
}
