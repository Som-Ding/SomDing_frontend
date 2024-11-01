package com.software.somding.network.api

import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.mypage.MyPageResponse
import com.software.somding.data.model.mypage.MyProjectOrderResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part

interface MyPageApi {
	@GET("api/member/profile")
	fun getMyPage(): Call<CommonResponse<MyPageResponse>>

	@Multipart
	@PATCH("api/member/profile")
	fun updateProfile(
		@Part("updateProfileDTO") updateProfileDTO: RequestBody,
		@Part image: MultipartBody.Part?
	): Call<CommonResponse<String>>

	@GET("api/orders/my")
	fun orderMyProject(): Call<CategoryProjectResponse>

	@GET("api/projects/scrap/my")
	fun getScrap(): Call<CategoryProjectResponse>
}