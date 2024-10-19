package net.branium.data.repository.impl

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.request.IntrospectRequest
import net.branium.data.model.dto.request.ResetPasswordRequest
import net.branium.data.model.dto.request.SignInRequest
import net.branium.data.model.dto.request.SignUpRequest
import net.branium.data.model.dto.response.ApiResponse
import net.branium.data.model.dto.response.ErrorResponse
import net.branium.data.model.dto.response.SignInResponse
import net.branium.data.repository.AuthRepository
import net.branium.data.retrofit.service.AuthApiService
import net.branium.data.model.dto.response.ResultResponse
import net.branium.di.TokenManager
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl
@Inject constructor(@Named("RetrofitInstance") private val retrofitInstance: Retrofit) :
    AuthRepository {

    private val authApiService: AuthApiService by lazy {
        retrofitInstance.create(AuthApiService::class.java)
    }

    override suspend fun signIn(request: SignInRequest, context: Context): ResultResponse<String> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.signIn(request)
            if (response.isSuccessful) {
                // save the token to SharePreference
                val token = response.body()?.result?.token
                val tokenManager = TokenManager(context)
                tokenManager.storeToken(token.toString())
                ResultResponse.Success(response.body()?.message)
            } else {
                val errorRes = parseErrorResponse(response)
                ResultResponse.Error(Exception(errorRes?.message))
            }
        }
    }

    override suspend fun signUp(request: SignUpRequest): ResultResponse<String> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.signUp(request)
            if (response.isSuccessful) {
                ResultResponse.Success("Sign up successful! Please check your email address to enable your account!")
            } else {
                ResultResponse.Error(Exception("Sign up failed due to something wrong!"))
            }
        }
    }

    override suspend fun introspectToken(request: IntrospectRequest): ResultResponse<Boolean> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.introspectToken(request)
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                val isValid = responseBody.result.valid
                ResultResponse.Success(isValid)
            } else {
                ResultResponse.Error(Exception("Something wrong!"))
            }
        }
    }

    override suspend fun sendResetEmail(resetEmail: String): ResultResponse<Any> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.resetPassword(resetEmail)
            if (response.isSuccessful) {
                ResultResponse.Success("Send email succeeded!")
            } else {
                ResultResponse.Error(Exception("Send email failed!"))
            }
        }
    }

    override suspend fun verifyCode(code: String): ResultResponse<Any> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.verifyCode(code)
            if (response.isSuccessful) {
                ResultResponse.Success("Verify succeeded!")
            } else {
                ResultResponse.Error(Exception("Verify failed!"))
            }
        }
    }

    override suspend fun resetPassword(request: ResetPasswordRequest): ResultResponse<String> {
        return withContext(Dispatchers.IO) {
            val response = authApiService.resetPassword(request)
            val responseBody = response.body()!!
            if (responseBody.code == 1000) {
                ResultResponse.Success(responseBody.message)
            } else {
                ResultResponse.Error(Exception(responseBody.message))
            }
        }
    }

    // Function to parse the error body to ErrorResponse object
    private fun parseErrorResponse(response: Response<ApiResponse<SignInResponse>>): ErrorResponse? {
        val converter = retrofitInstance
            .responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, arrayOfNulls(0))

        return try {
            response.errorBody()?.let {
                converter.convert(it) // Convert the error body to ErrorResponse
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}