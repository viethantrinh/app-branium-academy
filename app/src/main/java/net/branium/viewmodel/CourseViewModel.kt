package net.branium.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.course.CourseDetailResponse
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.model.dto.response.home.PopularCourse
import net.branium.data.repository.impl.CourseRepositoryImpI
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepositoryImpI,
) :
    ViewModel() {
    private var _apiResponseState = mutableStateOf<ApiResponseState?>(ApiResponseState.Processing)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    private var _listMyCourses = mutableStateOf<List<CourseResponse>>(emptyList())
    val listMyCourses: State<List<CourseResponse>> = _listMyCourses

    private var _courseDetail = mutableStateOf(CourseDetailResponse())
    val courseDetail: State<CourseDetailResponse> = _courseDetail

    private var _listPopularCourseDetails = mutableStateOf<List<CourseDetailResponse>>(emptyList())
    val listPopularCourseDetails: State<List<CourseDetailResponse>> = _listPopularCourseDetails

    init {
        getListMyCourses() // Gọi hàm này để lấy dữ liệu ngay khi ViewModel được khởi tạo

    }

    fun getListMyCourses() {
        viewModelScope.launch {
            when (val response = courseRepository.listMyCourses()) {
                is ResultResponse.Success -> {
                    _apiResponseState.value = ApiResponseState.Succeeded
                    _apiResponseState.value?.message = "load success"
                    _listMyCourses.value = response.data ?: emptyList()
                }

                is ResultResponse.Error -> {
                    _apiResponseState.value = ApiResponseState.Failed
                    _apiResponseState.value?.message = "load failed"
                    _listMyCourses.value = emptyList()
                }
            }
        }
    }

     fun getListPopularCourseDetails(popularCourses: List<PopularCourse>) {
        viewModelScope.launch {
            val courseDetails = popularCourses.map { course ->
                async { courseRepository.getCourseDetail(course.id) }
            }.awaitAll().mapNotNull { (it as? ResultResponse.Success)?.data }

            _listPopularCourseDetails.value = courseDetails
        }
    }

    fun getCourseDetail(courseId: Int) {
        viewModelScope.launch {
            when (val response = courseRepository.getCourseDetail(courseId)) {
                is ResultResponse.Success -> {
                    _apiResponseState.value = ApiResponseState.Succeeded
                    _apiResponseState.value?.message = "load success"
                    _courseDetail.value = response.data ?: CourseDetailResponse()
                }

                is ResultResponse.Error -> {
                    _apiResponseState.value = ApiResponseState.Failed
                    _apiResponseState.value?.message = "load failed"
                    _courseDetail.value = CourseDetailResponse()
                }
            }

        }
    }

    fun enrollInCourse(courseId: Int) {
        viewModelScope.launch {
            when (val response = courseRepository.enrollInCourse(courseId)) {
                is ResultResponse.Success -> {

                }

                is ResultResponse.Error -> {

                }
            }
        }
    }


    fun increaseCourseLearners(courseId: Int) {
        viewModelScope.launch {
            when (val response = courseRepository.increaseCourseLearners(courseId)) {
                is ResultResponse.Success -> {

                }

                is ResultResponse.Error -> {

                }
            }
        }
    }

}