package net.branium.data.repository

import net.branium.data.model.dto.ApiResponse
import net.branium.data.model.dto.ResetPasswordRequest
import net.branium.data.retrofit.ResultResponse
import net.branium.data.retrofit.RetrofitHelper

interface AuthRepository {
    suspend fun sendResetEmail(resetEmail: String): ResultResponse<Any>
    fun verifyCode(code: String): Boolean
    fun resetPassword(request: ResetPasswordRequest): Boolean
    suspend fun signIn()

}