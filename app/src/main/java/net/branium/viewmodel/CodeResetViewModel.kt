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

class CodeResetViewModel : ViewModel() {
    private val authRepository = AuthRepositoryImpl()
    var _verifySucceeded by mutableStateOf(false)
//    val verifySucceeded = _verifySucceeded

    suspend fun verifyCode(code: String) {
        val succeeded = authRepository.verifyCode(code)
    }


}