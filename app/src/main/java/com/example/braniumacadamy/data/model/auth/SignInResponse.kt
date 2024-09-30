package com.example.braniumacadamy.data.model.auth

data class SignInResponse(
    val code : Int,
    val message : String,
    val result : Result?  // la 1 token
)
