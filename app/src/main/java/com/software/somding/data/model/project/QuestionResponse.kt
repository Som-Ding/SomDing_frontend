package com.software.somding.data.model.project

data class QuestionResponse(
    val code: String,
    val message: String,
    val result: List<Question>
)

data class Question(
    val questionId: Int,
    val title: String,
    val question: String,
    val createdAt: String
)