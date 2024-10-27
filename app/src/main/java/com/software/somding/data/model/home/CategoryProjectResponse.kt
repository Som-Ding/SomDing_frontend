package com.software.somding.data.model.home

data class CategoryProjectResponse(
	val code: String,
	val message: String,
	val result: List<CategoryProjectData>
)

data class CategoryProjectData(
	val projectId: Int,
	val title: String,
	val img: String?,
	val category: String,
	val targetPrice: Int,
	val gatherPrice: Int,
	val price: Int
)