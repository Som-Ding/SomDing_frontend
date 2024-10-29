package com.software.somding.data.model.project

data class CreateQuestionRequestDTO(
	val projectId: Int,
	val title: String,
	val question: String
)