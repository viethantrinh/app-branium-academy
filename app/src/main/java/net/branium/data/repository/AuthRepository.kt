package net.branium.data.repository

import android.content.Context
import net.branium.data.model.dto.request.auth.IntrospectRequest
import net.branium.data.model.dto.request.auth.ResetPasswordRequest
import net.branium.data.model.dto.request.auth.SignInRequest
import net.branium.data.model.dto.request.auth.SignUpRequest
import net.branium.data.model.dto.response.base.ResultResponse

interface AuthRepository {
    suspend fun signIn(request: SignInRequest, context: Context): ResultResponse<String>
    suspend fun signUp(request: SignUpRequest): ResultResponse<String>
    suspend fun introspectToken(request: IntrospectRequest): ResultResponse<Boolean>
    suspend fun sendResetEmail(resetEmail: String): ResultResponse<Any>
    suspend fun verifyCode(code: String): ResultResponse<Any>
    suspend fun resetPassword(request: ResetPasswordRequest): ResultResponse<String>
}