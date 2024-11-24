package net.branium.data.model.dto.request.exam

import com.google.gson.annotations.SerializedName

data class ExamRequest (
    @SerializedName("quizId") val quizId: Int = 0,
    @SerializedName("answers") val answers: List<Answer> = emptyList()
)

/**
 * {
 *     "quizId": 21,
 *     "answers": [
 *         {
 *             "questionId": 1,
 *             "selectedOption": 2,
 *             "isCorrect": true
 *         },
 *         {
 *             "questionId": 2,
 *             "selectedOption": 2,
 *             "isCorrect": true
 *         },
 *         {
 *             "questionId": 3,
 *             "selectedOption": 2,
 *             "isCorrect": true
 *         },
 *         {
 *             "questionId": 4,
 *             "selectedOption": 2,
 *             "isCorrect": false
 *         },
 *         {
 *             "questionId": 5,
 *             "selectedOption": 2,
 *             "isCorrect": true
 *         },
 *         {
 *             "questionId": 6,
 *             "selectedOption": 2,
 *             "isCorrect": false
 *         },
 *         {
 *             "questionId": 7,
 *             "selectedOption": 2,
 *             "isCorrect": true
 *         },
 *         {
 *             "questionId": 8,
 *             "selectedOption": 2,
 *             "isCorrect": true
 *         },
 *         {
 *             "questionId": 9,
 *             "selectedOption": 2,
 *             "isCorrect": true
 *         },
 *         {
 *             "questionId": 10,
 *             "selectedOption": 1,
 *             "isCorrect": true
 *         }
 *     ]
 * }
 */