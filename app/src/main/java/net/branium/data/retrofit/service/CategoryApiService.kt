package net.branium.data.retrofit.service

import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.course.CourseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApiService {
    @GET("categories/{id}/courses")
    suspend fun getAllCoursesByCategoryId(@Path(value = "id") id: String) : Response<ApiResponse<List<CourseResponse>>>
}