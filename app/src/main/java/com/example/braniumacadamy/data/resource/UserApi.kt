package com.example.braniumacadamy.data.resource

import com.example.braniumacadamy.data.model.auth.SignInData
import com.example.braniumacadamy.data.model.auth.SignInResponse
import com.example.braniumacadamy.data.model.auth.SignUpData
import com.example.braniumacadamy.data.model.auth.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("auth/sign-in")
    suspend fun login(@Body signInData: SignInData): Response<SignInResponse>

    @POST("auth/sign-up")
    suspend fun signup(@Body signUpData: SignUpData): Response<SignUpResponse>
}



