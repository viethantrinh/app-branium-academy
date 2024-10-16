package net.branium.viewmodel


import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.branium.data.model.dto.request.SignInRequest
import net.branium.data.repository.AuthRepositoryImpl
import net.branium.data.retrofit.ResultResponse

class SignInViewModel : ViewModel() {
    private val authRepository = AuthRepositoryImpl()
    private var _apiResponseState = mutableStateOf<ApiResponseState?>(null)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    fun signIn(request: SignInRequest, context: Context) {
        viewModelScope.launch {
            when (val response = authRepository.signIn(request, context)) {
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