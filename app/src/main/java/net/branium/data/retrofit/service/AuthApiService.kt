package net.branium.data.retrofit.service

import net.branium.data.model.dto.request.auth.IntrospectRequest
import net.branium.data.model.dto.request.auth.ResetPasswordRequest
import net.branium.data.model.dto.request.auth.SignInRequest
import net.branium.data.model.dto.request.auth.SignUpRequest
import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.auth.IntrospectResponse
import net.branium.data.model.dto.response.auth.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    @POST(value = "auth/sign-in")
    suspend fun signIn(@Body request: SignInRequest): Response<ApiResponse<SignInResponse>>

    @POST(value = "auth/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): Response<ApiResponse<Void>>

    @POST(value = "auth/introspect-token")
    suspend fun introspectToken(@Body request: IntrospectRequest): Response<ApiResponse<IntrospectResponse>>

    @GET(value = "auth/reset-password")
    suspend fun resetPassword(@Query(value = "email") email: String): Response<Void>

    @GET(value = "auth/verify")
    suspend fun verifyCode(@Query(value = "code") code: String): Response<Void>

    @POST(value = "auth/reset-password")
    suspend fun resetPassword(@Body requestBody: ResetPasswordRequest): Response<ApiResponse<Void>>
}