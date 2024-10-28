package com.software.somding.data.model.project

import android.content.Context

data class ProjectDetailResponse(
	val code: String,
	val message: String,
	val result: ProjectDetail
)

data class ProjectDetail(
	val projectId: Int,
	val title: String,
	val category: String,
	val targetPrice: Int,
	val gatherPrice: Int,
	val targetDate: String,
	val sponsorNum: Int,
	val price: Int,
	val scrapNum: Int,
	val introduce: String,
	val policy: String,
	val schedule: String,
	val imgList: List<String>,
	val colorList: List<Option>,
	val sizeList: List<Option>,
	val otherList: List<Option>
)

data class Option(
	val optionId: Int,
	val optionCategory: String,
	val option: String
)
