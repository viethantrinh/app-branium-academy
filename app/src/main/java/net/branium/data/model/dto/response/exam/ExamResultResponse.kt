package net.branium.data.model.dto.response.exam;

data class ExamResultResponse(
    val result: String = "",
    val score: Double = 0.0,
    val time: String = ""
)

/**
 * {
 *         "result": "8/10",
 *         "score": 80.0,
 *         "time": "2024-11-10T13:54:00.15497653"
 *     }
 */
