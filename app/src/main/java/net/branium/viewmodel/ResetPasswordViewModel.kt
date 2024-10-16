package net.branium.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import net.branium.data.model.dto.ResetPasswordRequest
import net.branium.data.repository.AuthRepositoryImpl

class ResetPasswordViewModel : ViewModel() {
    private val authRepository = AuthRepositoryImpl()
    var _resetSucceeded by mutableStateOf(false)

    fun resetPassword(code: String, email: String, newPassword: String) {
        val request = ResetPasswordRequest(code = code, email = email, newPassword = newPassword)
        val succeeded = authRepository.resetPassword(request)
        _resetSucceeded = succeeded
    }
}