package net.branium.data.repository

import net.branium.data.model.dto.request.ResetPasswordRequest
import net.branium.data.model.dto.request.SignUpRequest
import net.branium.data.retrofit.ResultResponse

interface AuthRepository {
    suspend fun signUp(request: SignUpRequest): ResultResponse<String>
    suspend fun sendResetEmail(resetEmail: String): ResultResponse<Any>
    suspend fun verifyCode(code: String): ResultResponse<Any>
    suspend fun resetPassword(request: ResetPasswordRequest): ResultResponse<String>
    suspend fun signIn()

}