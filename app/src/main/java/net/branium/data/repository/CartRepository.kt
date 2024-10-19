package net.branium.data.repository

import net.branium.data.model.dto.response.CourseResponse
import net.branium.data.model.dto.response.ResultResponse

interface CartRepository {
    suspend fun listCartItems(): ResultResponse<List<CourseResponse>>
    suspend fun addCartItem(courseId: Int): ResultResponse<List<CourseResponse>>
    suspend fun removeCartItem(courseId: Int): ResultResponse<List<CourseResponse>>
}