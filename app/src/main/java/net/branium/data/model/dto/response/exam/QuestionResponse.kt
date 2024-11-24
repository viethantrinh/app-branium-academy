package net.branium.data.model.dto.response.exam

data class QuestionResponse(
    val id: Int,
    val title: String,
    val answer1: String,
    val isCorrect1: Boolean,
    val answer2: String,
    val isCorrect2: Boolean,
    val answer3: String,
    val isCorrect3: Boolean
)
