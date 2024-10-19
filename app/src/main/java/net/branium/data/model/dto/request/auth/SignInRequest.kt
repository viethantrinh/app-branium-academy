package net.branium.data.model.dto.request.auth

data class SignInRequest(
    val email: String = "",
    val password: String = ""
)
