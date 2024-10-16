package net.branium.data.model.dto.request


data class SignUpRequest(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = ""
)