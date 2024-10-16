package net.branium.viewmodel


import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.branium.data.repository.AuthRepository
import net.branium.data.repository.AuthRepositoryImpl
import net.branium.data.retrofit.ResultResponse

class ForgotPasswordViewModel : ViewModel() {
    private val authRepository = AuthRepositoryImpl()
    private var _isSent = mutableStateOf(false)
    val isSent: State<Boolean> = _isSent

    private var _message = mutableStateOf("")
    val message: State<String> = _message



    fun sendResetEmail(resetEmail: String) {
        viewModelScope.launch {
            val resultResponse = authRepository.sendResetEmail(resetEmail)
            _isSent.value = when (resultResponse) {
                is ResultResponse.Success -> {
                    _message.value = resultResponse.data.toString()
                    true
                }
                is ResultResponse.Error -> {
                    _message.value = resultResponse.exception.message.toString()
                    false
                }
                else -> false
            }
        }
    }

}