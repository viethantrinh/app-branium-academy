package net.branium.data.model.dto.response.user

data class UserInfoResponse(
    val id: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: Boolean = false,
    val dateOfBirth: String = "",
    val image: String = "",
    val phoneNumber: String = "",
    val roles: List<RoleResponse> = emptyList()
)

/**
 *  {
 *         "id": "4a39e0fe-27a0-4789-8ba2-b7e49677db34",
 *         "email": "hntrnn19@gmail.com",
 *         "firstName": "Linh",
 *         "lastName": "Khanh",
 *         "gender": false,
 *         "dateOfBirth": "2001-10-06",
 *         "image": "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/riftfWen",
 *         "phoneNumber": "0987666543",
 *         "roles": [
 *             {
 *                 "name": "STUDENT"
 *             }
 *         ]
 *     }
 */