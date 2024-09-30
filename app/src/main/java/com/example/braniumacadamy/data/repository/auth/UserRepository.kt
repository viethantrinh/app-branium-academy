package com.example.braniumacadamy.data.repository.auth

import com.example.braniumacadamy.data.model.auth.Result
import com.example.braniumacadamy.data.model.auth.SignInData
import com.example.braniumacadamy.data.model.auth.SignUpData
import com.example.braniumacadamy.data.resource.ResultResponse

interface UserRepository {
    suspend fun login(signInData: SignInData): ResultResponse<Result?>

    suspend fun signup(signUpData: SignUpData) :ResultResponse<String?>

}