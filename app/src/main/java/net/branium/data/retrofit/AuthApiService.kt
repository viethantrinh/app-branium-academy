package net.branium.data.retrofit

import net.branium.data.model.dto.response.ApiResponse
import net.branium.data.model.dto.request.ResetPasswordRequest
import net.branium.data.model.dto.request.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    @POST(value = "auth/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): Response<ApiResponse<Void>>

    @GET(value = "auth/reset-password")
    suspend fun resetPassword(@Query(value = "email") email: String): Response<Void>

    @GET(value = "auth/verify")
    suspend fun verifyCode(@Query(value = "code") code: String): Response<Void>

    @POST(value = "auth/reset-password")
    suspend fun resetPassword(@Body requestBody: ResetPasswordRequest): Response<ApiResponse<Void>>
}