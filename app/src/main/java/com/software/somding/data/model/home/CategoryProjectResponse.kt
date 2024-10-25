package com.software.somding.data.model.home

data class CategoryProjectResponse(
    val projectId: Int,
    val title: String,
    val img: String,
    val category: String,
    val targetPrice: Int,
    val gatherPrice: Int,
    val price: Int,
    val orderId: Int
)