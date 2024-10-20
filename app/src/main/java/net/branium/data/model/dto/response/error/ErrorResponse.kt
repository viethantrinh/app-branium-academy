package net.branium.data.model.dto.response.error

data class ErrorResponse(
    val code: Int,
    val message: String,
    val timeStamp: String,
    val path: String,
    val errors: List<String> = listOf(),

)
