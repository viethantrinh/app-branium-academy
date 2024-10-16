package net.branium.data.retrofit

import net.branium.data.model.dto.request.IntrospectRequest
import net.branium.data.model.dto.response.ApiResponse
import net.branium.data.model.dto.request.ResetPasswordRequest
import net.branium.data.model.dto.request.SignInRequest
import net.branium.data.model.dto.request.SignUpRequest
import net.branium.data.model.dto.response.IntrospectResponse
import net.branium.data.model.dto.response.SignInResponse
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