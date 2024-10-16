package net.branium.data.repository

import net.branium.data.model.dto.request.ResetPasswordRequest
import net.branium.data.retrofit.ResultResponse

interface AuthRepository {
    suspend fun sendResetEmail(resetEmail: String): ResultResponse<Any>
    suspend fun verifyCode(code: String): ResultResponse<Any>
    suspend fun resetPassword(request: ResetPasswordRequest): ResultResponse<String>
    suspend fun signIn()

}