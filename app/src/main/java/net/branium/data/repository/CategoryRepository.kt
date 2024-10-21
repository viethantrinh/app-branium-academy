package net.branium.data.repository

import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.course.CourseResponse

interface CategoryRepository {
    suspend fun getAllCoursesByCategoryId(categoryId: String): ResultResponse<List<CourseResponse>>
}