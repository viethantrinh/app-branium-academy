package net.branium.data.model.dto.request


data class ResetPasswordRequest(
    val email: String = "",
    val newPassword: String = "",
    val code: String = ""
)