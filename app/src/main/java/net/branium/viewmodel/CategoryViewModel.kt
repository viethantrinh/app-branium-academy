package net.branium.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.repository.impl.CategoryRepositoryImpI
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepositoryImpI) :
    ViewModel() {
    private var _apiResponseState = mutableStateOf<ApiResponseState?>(null)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    private var _coursesByCategoryId = mutableStateOf(emptyList<CourseResponse>())
    val coursesByCategoryId: State<List<CourseResponse>> = _coursesByCategoryId


    fun getAllCoursesByCategoryId(categoryId: String) {
        viewModelScope.launch {
            when (val response = categoryRepository.getAllCoursesByCategoryId(categoryId)) {
                is ResultResponse.Success -> {
                    _apiResponseState.value = ApiResponseState.Succeeded
                    _apiResponseState.value?.message = "Succeeded"
                    _coursesByCategoryId.value = response.data ?: emptyList()

                }

                is ResultResponse.Error -> {
                    _apiResponseState.value = ApiResponseState.Failed
                    _apiResponseState.value?.message = "Failed"
                    _coursesByCategoryId.value = emptyList()
                }

                else -> {
                    _apiResponseState.value = ApiResponseState.Processing
                    _apiResponseState.value?.message = "Processing"
                }
            }
        }
    }

}