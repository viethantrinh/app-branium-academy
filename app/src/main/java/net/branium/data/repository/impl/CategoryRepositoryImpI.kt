package net.branium.data.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.repository.CategoryRepository
import net.branium.data.retrofit.service.CategoryApiService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class CategoryRepositoryImpI
@Inject constructor(@Named("RetrofitInstanceWithAuthInterceptor") val retrofitInstance: Retrofit) :
    CategoryRepository {
        private val categoryApiService: CategoryApiService by lazy {
            retrofitInstance.create(CategoryApiService::class.java)
        }
    override suspend fun getAllCoursesByCategoryId(categoryId: String): ResultResponse<List<CourseResponse>> {
        return withContext(Dispatchers.IO) {
            val response = categoryApiService.getAllCoursesByCategoryId(categoryId)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null){
                    if(apiResponse.code == 1000){
                        ResultResponse.Success(apiResponse.result)
                    }else{
                        ResultResponse.Error(Exception(apiResponse.message))
                    }
                } else {
                    ResultResponse.Error(Exception("Response body is null"))
                }
            } else {
                ResultResponse.Error(Exception("Something went wrong"))
            }
        }
    }
}