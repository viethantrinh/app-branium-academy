package net.branium.data.model.dto.homepage

data class TopPick(
    val id: Int,
    val title: String,
    val image: String,
    val price: Double,
    val discountPrice: Double
)


/**
 * {
 *                 "id": 1,
 *                 "title": "Pro Flutter 2025",
 *                 "image": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/XgYqkzQJ",
 *                 "price": 4499000.00,
 *                 "discountPrice": 1999000.00
 *             }
 */