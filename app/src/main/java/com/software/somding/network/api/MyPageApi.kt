package com.software.somding.network.api

import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.mypage.MyPageResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyPageApi {
	@GET("api/member/profile")
	fun getMyPage(): Call<CommonResponse<MyPageResponse>>
}