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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.branium.data.repository.AuthRepository
import net.branium.data.repository.AuthRepositoryImpl
import net.branium.data.retrofit.ResultResponse

class CodeResetViewModel : ViewModel() {
    private val authRepository = AuthRepositoryImpl()
    private var _apiResponseState = mutableStateOf<ApiResponseState?>(null)
    val apiResponseState: State<ApiResponseState?> = _apiResponseState

    fun verifyCode(code: String) {
        viewModelScope.launch {
            when (val response = authRepository.verifyCode(code)) {
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