package net.branium.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.branium.data.model.dto.ResetPasswordRequest
import net.branium.data.repository.AuthRepositoryImpl
import net.branium.data.retrofit.ResultResponse

class ResetPasswordViewModel : ViewModel() {
    private val authRepository = AuthRepositoryImpl()
    private var _apiResponseState = mutableStateOf<ApiResponseState?>(null)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    fun resetPassword(code: String, email: String, newPassword: String) {
        viewModelScope.launch {
            val requestBody = ResetPasswordRequest(
                code = code,
                email = email,
                newPassword = newPassword
            )

            when (val response = authRepository.resetPassword(requestBody)) {
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