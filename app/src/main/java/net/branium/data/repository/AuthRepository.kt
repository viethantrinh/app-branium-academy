package net.branium.data.repository

import android.content.Context
import net.branium.data.model.dto.request.IntrospectRequest
import net.branium.data.model.dto.request.ResetPasswordRequest
import net.branium.data.model.dto.request.SignInRequest
import net.branium.data.model.dto.request.SignUpRequest
import net.branium.data.model.dto.response.ResultResponse

interface AuthRepository {
    suspend fun signIn(request: SignInRequest, context: Context): ResultResponse<String>
    suspend fun signUp(request: SignUpRequest): ResultResponse<String>
    suspend fun introspectToken(request: IntrospectRequest): ResultResponse<Boolean>
    suspend fun sendResetEmail(resetEmail: String): ResultResponse<Any>
    suspend fun verifyCode(code: String): ResultResponse<Any>
    suspend fun resetPassword(request: ResetPasswordRequest): ResultResponse<String>
}