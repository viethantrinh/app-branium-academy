package net.branium.data.retrofit.service

import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.course.CourseDetailResponse
import net.branium.data.model.dto.response.course.CourseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CoursesApiService {
    @GET(value = "courses")
    suspend fun getAllBoughtCourses(): Response<ApiResponse<List<CourseResponse>>>

    @GET(value = "courses/{id}")
    suspend fun getCourseDetails(@Path(value = "id") courseId: Int): Response<ApiResponse<CourseDetailResponse>>

    @POST(value = "courses/{id}/enroll")
    suspend fun enrollInCourse(@Path(value = "id") courseId: Int): Response<ApiResponse<Boolean>>

    @POST(value = "courses/{id}/go-to-course")
    suspend fun increaseCourseLearnersById(@Path(value = "id") courseId: Int): Response<ApiResponse<Int>>
}