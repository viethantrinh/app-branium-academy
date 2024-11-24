package net.branium.data.repository

import net.branium.data.model.dto.request.exam.ExamRequest
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.exam.ExamResponse
import net.branium.data.model.dto.response.exam.ExamResultResponse

interface ExamRepository {
    suspend fun getExamData(examId : Int) : ResultResponse<ExamResponse>

    suspend fun submitQuizResult(examRequest: ExamRequest) : ResultResponse<ExamResultResponse>
}