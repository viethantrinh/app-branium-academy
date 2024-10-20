package net.branium.data.model.dto.response.home

data class PopularCourse(
    val id: Int,
    val title: String,
    val image: String,
    val price: Double,
    val discountPrice: Double,
    val totalStudents: Int
)

/**
 * {
 *                 "id": 9,
 *                 "title": "Cấu trúc dữ liệu và Giải thuật",
 *                 "image": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/3IY3X2sK",
 *                 "price": 1799000.00,
 *                 "discountPrice": 729000.00,
 *                 "totalStudents": 0
 *             }
 */
