package net.branium.data.model.dto.request.exam

import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("questionId") var questionId: Int = 0,
    @SerializedName("selectedOption") var selectedOption: Int = 0,
    @SerializedName("isCorrect")var isCorrect: Boolean = false
)

/**
 *  {
 *  *             "questionId": 1,
 *  *             "selectedOption": 2,
 *  *             "isCorrect": true
 *  *         }
 */