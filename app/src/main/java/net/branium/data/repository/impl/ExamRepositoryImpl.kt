package net.branium.data.repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.request.exam.ExamRequest
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.exam.ExamResponse
import net.branium.data.model.dto.response.exam.ExamResultResponse
import net.branium.data.repository.ExamRepository
import net.branium.data.retrofit.service.ExamApiService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class ExamRepositoryImpl
@Inject constructor(@Named("RetrofitInstanceWithAuthInterceptor") private val retrofitInstanceWithAuthInterceptor: Retrofit):
   ExamRepository  {
    private val examApiService: ExamApiService by lazy {
        retrofitInstanceWithAuthInterceptor.create(ExamApiService::class.java)
    }

    override suspend fun getExamData(examId: Int): ResultResponse<ExamResponse> {
        return withContext(Dispatchers.IO) {
            val response = examApiService.getExamData(examId)
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

//    override suspend fun submitQuizResult(examRequest: ExamRequest): ResultResponse<ExamResultResponse> {
//        return withContext(Dispatchers.IO) {
//            val response = examApiService.submitQuizResult(examRequest)
//            if (response.isSuccessful) {
//                val apiRepository = response.body()
//                if (apiRepository != null) {
//                    if (apiRepository.code == 1000) {
//                        ResultResponse.Success(apiRepository.result)
//                    } else {
//                        ResultResponse.Error(Exception(apiRepository.message))
//                    }
//                } else {
//                    ResultResponse.Error(Exception("Something went wrong"))
//                }
//            } else {
//                ResultResponse.Error(Exception("No Internet Connection"))
//            }
//        }
//    }

    override suspend fun submitQuizResult(examRequest: ExamRequest): ResultResponse<ExamResultResponse> {
        return withContext(Dispatchers.IO) {
            val response = examApiService.submitQuizResult(examRequest)
            if (response.isSuccessful) {
                val apiRepository = response.body()
                Log.d("ExamRepository", "API Response: $apiRepository")
                if (apiRepository != null) {
                    if (apiRepository.code == 1000) {
                        ResultResponse.Success(apiRepository.result)
                    } else {
                        ResultResponse.Error(Exception(apiRepository.message))
                    }
                } else {
                    ResultResponse.Error(Exception("Response body is null"))
                }
            } else {
                Log.e("ExamRepository", "HTTP Error: ${response.code()} ${response.message()}")
                ResultResponse.Error(Exception("API error: ${response.code()} ${response.message()}"))
            }
        }
    }

}