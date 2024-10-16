package net.branium.data.repository

import net.branium.data.model.dto.ApiResponse
import net.branium.data.model.dto.ResetPasswordRequest
import net.branium.data.retrofit.ResultResponse
import net.branium.data.retrofit.RetrofitHelper

interface AuthRepository {
    suspend fun sendResetEmail(resetEmail: String): ResultResponse<Any>
    suspend fun verifyCode(code: String): ResultResponse<Any>
    fun resetPassword(request: ResetPasswordRequest): Boolean
    suspend fun signIn()

}