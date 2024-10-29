package com.software.somding.data.model.mypage

data class MyProjectOrderResponse(
	val code: String,
	val message: String,
	val result: List<MyProjectOrderData>
)

data class MyProjectOrderData(
	val projectId: Int,
	val title: String,
	val img: String?,
	val category: String,
	val targetPrice: Int,
	val gatherPrice: Int,
	val price: Int
)