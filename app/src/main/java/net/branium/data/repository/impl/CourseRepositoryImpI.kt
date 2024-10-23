package net.branium.data.repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.course.CourseDetailResponse
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.repository.CourseRepository
import net.branium.data.retrofit.service.CoursesApiService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class CourseRepositoryImpI
@Inject constructor(@Named("RetrofitInstanceWithAuthInterceptor") private val retrofitInstanceWithAuthInterceptor: Retrofit) :
    CourseRepository {

    private val courseApiService: CoursesApiService by lazy {
        retrofitInstanceWithAuthInterceptor.create(CoursesApiService::class.java)
    }

    override suspend fun listMyCourses(): ResultResponse<List<CourseResponse>> {
        return withContext(Dispatchers.IO) {
            val response = courseApiService.getAllBoughtCourses()
            if (response.isSuccessful) {
                val apiRepository = response.body()
                if (apiRepository != null) {
                    if (apiRepository.code == 1000) {
                        ResultResponse.Success(apiRepository.result)
                    } else {
                        ResultResponse.Error(Exception(apiRepository.message))
                    }
                } else {
                    ResultResponse.Error(Exception("Something went wrong"))
                }
            } else {
                ResultResponse.Error(Exception("No Internet Connection"))
            }
        }
    }

    override suspend fun getCourseDetail(courseId: Int): ResultResponse<CourseDetailResponse> {
        return withContext(Dispatchers.IO) {
            val response = courseApiService.getCourseDetails(courseId)
            if (response.isSuccessful) {
                val apiRepository = response.body()
                if (apiRepository != null) {
                    if (apiRepository.code == 1000) {
                        Log.d("TAG", "getCourseDetail: thanh cong")
                        ResultResponse.Success(apiRepository.result)
                    } else {
                        Log.d("TAG", "getCourseDetail: that bai")
                        ResultResponse.Error(Exception(apiRepository.message))
                    }
                } else {
                    Log.d("TAG", "getCourseDetail: that bai")
                    ResultResponse.Error(Exception("Something went wrong"))
                }
            } else {
                Log.d("TAG", "getCourseDetail: that bai")
                ResultResponse.Error(Exception("No Internet Connection"))
            }
        }
    }

    override suspend fun enrollInCourse(courseId: Int): ResultResponse<Boolean> {
        return withContext(Dispatchers.IO) {
            val response = courseApiService.enrollInCourse(courseId)
            if (response.isSuccessful) {
                val apiRepository = response.body()
                if (apiRepository != null) {
                    if (apiRepository.code == 1000) {
                        ResultResponse.Success(apiRepository.result)
                    } else {
                        ResultResponse.Error(Exception(apiRepository.message))
                    }
                } else {
                    ResultResponse.Error(Exception("Something went wrong"))
                }
            } else {
                ResultResponse.Error(Exception("No Internet Connection"))
            }
        }
    }

    override suspend fun increaseCourseLearners(courseId: Int): ResultResponse<Int> {
        return withContext(Dispatchers.IO) {
            val response = courseApiService.increaseCourseLearnersById(courseId)
            if (response.isSuccessful) {
                val apiRepository = response.body()
                if (apiRepository != null) {
                    if (apiRepository.code == 1000){
                        ResultResponse.Success(apiRepository.result)
                    }else{
                        ResultResponse.Error(Exception(apiRepository.message))
                    }
                } else {
                    ResultResponse.Error(Exception("Something went wrong"))
                }
            } else {
                ResultResponse.Error(Exception("No Internet Connection"))
            }
        }
    }
}