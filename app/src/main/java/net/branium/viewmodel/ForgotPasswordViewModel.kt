package net.branium.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.repository.impl.AuthRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val authRepository: AuthRepositoryImpl) : ViewModel() {
    private var _emailSentState = mutableStateOf<EmailSentState>(EmailSentState.Loading)
    val emailSentState: State<EmailSentState> = _emailSentState

    private var _message = mutableStateOf<String>("")
    val message: State<String> = _message


    fun sendResetEmail(resetEmail: String) {
        viewModelScope.launch {
            val resultResponse = authRepository.sendResetEmail(resetEmail)
            when (resultResponse) {
                is ResultResponse.Success -> {
                    _emailSentState.value = EmailSentState.SentSucceeded
                    _message.value = resultResponse.data.toString()
                }

                is ResultResponse.Error -> {
                    _emailSentState.value = EmailSentState.SentUnSucceeded
                    _message.value = resultResponse.exception.message.toString()
                }

                else -> {
                    _emailSentState.value = EmailSentState.Loading
                    _message.value = "Loading"
                }
            }
        }
    }

    sealed class EmailSentState {
        object SentSucceeded : EmailSentState()
        object SentUnSucceeded : EmailSentState()
        object Loading : EmailSentState()
    }

}