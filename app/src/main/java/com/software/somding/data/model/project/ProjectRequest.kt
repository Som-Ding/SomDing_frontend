package com.software.somding.data.model.project

data class ProjectRequest (
	val title: String,
	val introduce: String,
	val policy: String,
	val schedule: String,
	val category: String,
	val targetPrice: Int,
	val price: Int,
	val targetDate: String,
	val colorList: List<String>,
	val sizeList: List<String>,
	val otherList: List<String>,
	val images: List<String>
)