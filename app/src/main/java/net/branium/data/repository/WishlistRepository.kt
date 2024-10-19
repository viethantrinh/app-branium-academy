package net.branium.data.repository

import net.branium.data.model.dto.response.CourseResponse
import net.branium.data.model.dto.response.ResultResponse

interface WishlistRepository {
    suspend fun listWishlistItems(): ResultResponse<List<CourseResponse>>
    suspend fun addWishlistItem(courseId: Int): ResultResponse<List<CourseResponse>>
    suspend fun removeWishlistItem(courseId: Int): ResultResponse<List<CourseResponse>>
}