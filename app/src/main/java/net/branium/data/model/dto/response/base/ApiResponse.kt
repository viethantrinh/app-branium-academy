package net.branium.data.model.dto.response.base

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val result: T
)