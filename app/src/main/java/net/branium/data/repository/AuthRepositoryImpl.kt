package net.branium.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.ApiResponse
import net.branium.data.model.dto.ResetPasswordRequest
import net.branium.data.retrofit.AuthApiService
import net.branium.data.retrofit.ResultResponse
import net.branium.data.retrofit.RetrofitHelper

class AuthRepositoryImpl : AuthRepository {
    private val authApiService: AuthApiService by lazy {
        RetrofitHelper.getInstance().create(AuthApiService::class.java)
    }

    override suspend fun sendResetEmail(resetEmail: String): ResultResponse<Any> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.resetPassword(resetEmail)
            if (response.isSuccessful) {
                ResultResponse.Success("Send email succeeded!")
            } else {
                ResultResponse.Error(Exception("Send email failed!"))
            }
        }
    }

    override suspend fun verifyCode(code: String): ResultResponse<Any> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.verifyCode(code)
            if (response.isSuccessful) {
                ResultResponse.Success("Verify succeeded!")
            } else {
                ResultResponse.Error(Exception("Verify failed!"))
            }
        }
    }

    override suspend fun resetPassword(request: ResetPasswordRequest): ResultResponse<String> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.resetPassword(request)
            val responseBody = response.body()!!
            if (responseBody.code == 1000) {
                ResultResponse.Success(responseBody.message)
            } else {
                ResultResponse.Error(Exception(responseBody.message))
            }
        }
    }

    override suspend fun signIn() {
    }
}