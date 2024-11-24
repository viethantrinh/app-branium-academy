package net.branium.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.branium.data.model.dto.request.exam.ExamRequest
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.exam.ExamResponse
import net.branium.data.model.dto.response.exam.ExamResultResponse
import net.branium.data.repository.impl.ExamRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class ExamViewmodel @Inject constructor(
    private val examRepository: ExamRepositoryImpl
): ViewModel(){
    private val _examData = MutableStateFlow(ExamResponse())
    val examData = _examData.asStateFlow()

    private val _examResult = MutableStateFlow(ExamResultResponse())
    val examResult = _examResult.asStateFlow()

    private var _apiResponseState = MutableStateFlow<ApiResponseState?>(null)
    val apiResponseState = _apiResponseState.asStateFlow()

    fun getExamData(examId: Int){
        viewModelScope.launch {
            when(val response = examRepository.getExamData(examId)){
                is ResultResponse.Success -> {
                    _examData.value = response.data?: ExamResponse()
                }
                is ResultResponse.Error -> {
                    _examData.value = ExamResponse()
                }
                else -> {}
            }
        }
    }

    fun submitExamResult(examRequest: ExamRequest){
        viewModelScope.launch {
            when(val response = examRepository.submitQuizResult(examRequest)){
                is ResultResponse.Success -> {
                    _apiResponseState.value = ApiResponseState.Succeeded
                    _examResult.value = response.data ?: ExamResultResponse()
                }
                is ResultResponse.Error -> {
                    _apiResponseState.value = ApiResponseState.Failed
                    _examResult.value = ExamResultResponse()
                }
                else -> {
                    _apiResponseState.value = ApiResponseState.Processing
                }
            }
        }
    }

}