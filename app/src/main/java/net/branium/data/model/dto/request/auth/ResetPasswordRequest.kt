package net.branium.data.model.dto.request.auth


data class ResetPasswordRequest(
    val email: String = "",
    val newPassword: String = "",
    val code: String = ""
)