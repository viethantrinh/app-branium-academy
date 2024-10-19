package net.branium.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.branium.data.model.dto.request.SignUpRequest
import net.branium.data.repository.impl.AuthRepositoryImpl
import net.branium.data.model.dto.response.ResultResponse
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepositoryImpl) : ViewModel() {

    private var _apiResponseState = mutableStateOf<ApiResponseState?>(null)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    fun signUp(request: SignUpRequest) {
        viewModelScope.launch {
            when (val response = authRepository.signUp(request)) {
                is ResultResponse.Success -> {
                    _apiResponseState.value = ApiResponseState.Succeeded
                    _apiResponseState.value?.message = response.data.toString()
                }

                is ResultResponse.Error -> {
                    _apiResponseState.value = ApiResponseState.Failed
                    _apiResponseState.value?.message = response.exception.message.toString()
                }

                else -> {
                    _apiResponseState.value = ApiResponseState.Processing
                    _apiResponseState.value?.message = "Processing"
                }
            }
        }
    }
}