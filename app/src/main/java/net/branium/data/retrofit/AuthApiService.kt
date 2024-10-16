package net.branium.data.retrofit

import net.branium.data.model.dto.ApiResponse
import net.branium.data.model.dto.ResetPasswordRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    @GET(value = "auth/reset-password")
    suspend fun resetPassword(@Query(value = "email") email: String): Response<Void>

    @GET(value = "auth/verify")
    suspend fun verifyCode(@Query(value = "code") code: String): Response<Void>

    @POST(value = "auth/reset-password")
    suspend fun resetPassword(@Body requestBody: ResetPasswordRequest): Response<ApiResponse<Void>>
}