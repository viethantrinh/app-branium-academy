package net.branium.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.branium.data.model.dto.request.IntrospectRequest
import net.branium.data.repository.impl.AuthRepositoryImpl
import net.branium.data.model.dto.response.ResultResponse
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val authRepository: AuthRepositoryImpl) :
    ViewModel() {
    private var _apiResponseState = mutableStateOf<ApiResponseState?>(null)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState


    private var _authState = mutableStateOf<AuthState>(AuthState.Processing)
    val authState: State<AuthState> = _authState

    fun introspectToken(request: IntrospectRequest) {
        viewModelScope.launch {
            when (val response = authRepository.introspectToken(request)) {
                is ResultResponse.Success -> {
                    _apiResponseState.value = ApiResponseState.Succeeded
                    if (response.data == true) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value = AuthState.UnAuthenticated
                    }
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

    sealed class AuthState() {
        object Authenticated : AuthState()
        object UnAuthenticated : AuthState()
        object Processing : AuthState()
    }
}