package com.software.somding.data.model.project

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

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
	val colorList: List<ProjectOption>,
	val sizeList: List<ProjectOption>,
	val otherList: List<ProjectOption>
) {
	fun getRemainingDays(): Long {
		val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
		val targetLocalDate = LocalDate.parse(targetDate, formatter)
		val currentDate = LocalDate.now()
		val remainingDays = ChronoUnit.DAYS.between(currentDate, targetLocalDate)

		return if (remainingDays < 0) {
			0
		} else {
			remainingDays
		}
	}
}

data class Option(
	val optionId: Int,
	val optionCategory: String,
	val option: String
)
