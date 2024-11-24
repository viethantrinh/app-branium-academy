package net.branium.data.retrofit.service

import net.branium.data.model.dto.request.exam.ExamRequest
import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.exam.ExamResponse
import net.branium.data.model.dto.response.exam.ExamResultResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExamApiService {
    @GET(value = "quizzes/{id}")
    suspend fun getExamData(@Path(value = "id") examId: Int) : Response<ApiResponse<ExamResponse>>

    @POST(value = "quizzes/submit")
    suspend fun submitQuizResult(@Body examRequest: ExamRequest) : Response<ApiResponse<ExamResultResponse>>

}