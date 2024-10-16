package net.branium.data.model.dto


data class ResetPasswordRequest(
    val email: String = "",
    val newPassword: String = "",
    val code: String = ""
)