package net.branium.data.model.dto.response.course

data class Section(
    val id: Int,
    val title: String,
    val order: Int,
    val lectures: List<Lecture>
)


/**
 * {
 *                 "id": 1,
 *                 "title": "Chương 1. Nhập môn và cài đặt",
 *                 "order": 1,
 *                 "lectures": [
 *                     {
 *                         "id": 1,
 *                         "title": "Bài 1.1. Cài đặt Android Studio cho máy Windows",
 *                         "order": 1,
 *                         "type": "video",
 *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *                         "duration": "06:22"
 *                     },
 *                     {
 *                         "id": 2,
 *                         "title": "Bài 1.2. Cài đặt Android Studio cho máy Mac",
 *                         "order": 2,
 *                         "type": "video",
 *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *                         "duration": "06:22"
 *                     },
 *                     {
 *                         "id": 3,
 *                         "title": "Bài 1.3. Cài đặt Dart SDK và Flutter SDK",
 *                         "order": 3,
 *                         "type": "video",
 *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *                         "duration": "06:22"
 *                     },
 *                     {
 *                         "id": 4,
 *                         "title": "Bài 1.4. Cài đặt Flutter cho máy Mac",
 *                         "order": 4,
 *                         "type": "video",
 *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *                         "duration": "06:22"
 *                     },
 *                     {
 *                         "id": 5,
 *                         "title": "Bài 1.5. Cấu hình máy ảo iOS",
 *                         "order": 5,
 *                         "type": "video",
 *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *                         "duration": "06:22"
 *                     },
 *                     {
 *                         "id": 6,
 *                         "title": "Bài 1.6. Nội quy sử dụng khóa học",
 *                         "order": 6,
 *                         "type": "video",
 *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *                         "duration": "06:22"
 *                     },
 *                     {
 *                         "id": 7,
 *                         "title": "Bài 1.7. Hướng dẫn Coding conventions",
 *                         "order": 7,
 *                         "type": "video",
 *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *                         "duration": "06:22"
 *                     },
 *                     {
 *                         "id": 8,
 *                         "title": "Bài 1.8. Hướng dẫn về viết tài liệu",
 *                         "order": 8,
 *                         "type": "video",
 *                         "resource": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/videos/pzIeMntY",
 *                         "duration": "06:22"
 *                     }
 *                 ]
 *             }
 */