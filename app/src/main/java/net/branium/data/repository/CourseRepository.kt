package net.branium.data.repository

import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.course.CourseDetailResponse
import net.branium.data.model.dto.response.course.CourseResponse

interface CourseRepository {
    suspend fun listMyCourses(): ResultResponse<List<CourseResponse>>
    suspend fun getCourseDetail(courseId: Int): ResultResponse<CourseDetailResponse>
    suspend fun enrollInCourse(courseId: Int): ResultResponse<Boolean>
    suspend fun increaseCourseLearners(courseId: Int) :ResultResponse<Int>
}