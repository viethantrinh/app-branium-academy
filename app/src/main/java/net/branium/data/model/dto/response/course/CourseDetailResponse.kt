package net.branium.data.model.dto.response.course

data class CourseDetailResponse(
    val id: Int = 0,
    val title: String = "",
    val image: String = "",
    val shortDescription: String = "",
    val fullDescription: String = "",
    val price: Double = 0.0,
    val discountPrice: Double = 0.0,
    val updatedAt: String = "",
    val totalStudents: Int = 0,
    val totalSections: Int = 0,
    val totalLectures: Int = 0,
    val totalDuration: String = "",
    val paid: Boolean = false,
    val enrolled: Boolean = false,
    val inCart: Boolean = false,
    var inWishList: Boolean = false,
    val sections: List<Section> = emptyList()
)

/**
 *  {
 *         "id": 11,
 *         "title": "Cấu trúc dữ liệu và Giải thuật với Python",
 *         "image": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/IIxQqy9w",
 *         "shortDescription": "Đây là khóa học cấu trúc dữ liệu và giải thuật với Python.",
 *         "fullDescription": "Khóa học được lồng ghép các yêu cầu nâng cao vào các bài học để bạn có thể đáp ứng được yêu cầu của nhà tuyển dụng ngày càng tăng#Sau khi kết thúc khóa học bạn có thể tự tin đi thực tập, intern, fresher vị trí Mobile App Developer với Flutter#Khóa học sẽ được cập nhật, thay đổi nội dung mới nhất mà không có thông báo trước",
 *         "price": 1799000.00,
 *         "discountPrice": 729000.00,
 *         "updatedAt": null,
 *         "totalStudents": 0,
 *         "totalSections": 0,
 *         "totalLectures": 0,
 *         "totalDuration": null,
 *         "paid": false,
 *         "enrolled": false,
 *         "inCart": false,
 *         "inWishList": false,
 *         "sections": []
 *     }
 */