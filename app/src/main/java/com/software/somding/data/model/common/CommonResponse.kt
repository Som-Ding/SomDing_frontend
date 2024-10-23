package com.software.somding.data.model.common

data class CommonResponse<T>(
    val code: String,
    val message: String,
    val result: T
)
