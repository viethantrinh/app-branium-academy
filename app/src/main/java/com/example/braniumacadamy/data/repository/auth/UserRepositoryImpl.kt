package com.example.braniumacadamy.data.repository.auth

import com.example.braniumacadamy.data.model.auth.Result
import com.example.braniumacadamy.data.model.auth.SignInData
import com.example.braniumacadamy.data.model.auth.SignUpData
import com.example.braniumacadamy.data.resource.ResultResponse
import com.example.braniumacadamy.data.resource.RetrofitHelper
import com.example.braniumacadamy.data.resource.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl: UserRepository {
    override suspend fun login(signInData: SignInData): ResultResponse<Result?> {
        return withContext(Dispatchers.IO){
            val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)
            val response = userApi.login(signInData)
            if(response.isSuccessful){
                val signInResponse = response.body()!!
                if (signInResponse.code == 1000){
                    ResultResponse.Success(signInResponse.result)
                }else{
                    ResultResponse.Error(Exception(signInResponse.message))
                }
            }else{
                ResultResponse.Error(Exception("Unknown error"))
            }
        }
    }

    override suspend fun signup(signUpData: SignUpData): ResultResponse<String?> {
        return withContext(Dispatchers.IO){
            val userApi = RetrofitHelper.getInstance().create(UserApi::class.java)
            val response = userApi.signup(signUpData)
            if(response.isSuccessful){
                val result = response.body()!!
                if(result.code == 1000){
                    ResultResponse.Success(result.message)
                }else{
                    ResultResponse.Error(Exception(result.message))
                }
            }else{
                ResultResponse.Error(Exception("Unknown error"))
            }
        }
    }
}