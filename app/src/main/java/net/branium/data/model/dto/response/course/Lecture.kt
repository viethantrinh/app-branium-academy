package net.branium.data.model.dto.response.course

data class Lecture(
    val id: Int,
    val title: String,
    val order: Int,
    val type: String,
    val resource: String,
    val duration: String
)

/**
 * {
 *  *                         "id": 1,
 *  *                         "title": "Bài 1.1. Cài đặt Android Studio cho máy Windows",
 *  *                         "order": 1,
 *  *                         "type": "video",
 *  *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *  *                         "duration": "06:22"
 *  *                     }
 */