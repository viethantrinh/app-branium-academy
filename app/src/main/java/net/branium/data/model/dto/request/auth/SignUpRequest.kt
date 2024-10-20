package net.branium.data.model.dto.request.auth


data class SignUpRequest(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = ""
)