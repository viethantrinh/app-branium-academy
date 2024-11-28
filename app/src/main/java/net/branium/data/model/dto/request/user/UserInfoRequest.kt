package net.branium.data.model.dto.request.user

data class UserInfoRequest(
    val firstName: String ="",
    val lastName: String ="",
    val gender: Boolean = false,
    val dateOfBirth: String = "",
    val phoneNumber: String =""
)

/**
 * {
 *     "firstName": "Phuong Anh Beo",
 *     "lastName": "Nguyen",
 *     "gender": false,
 *     "dateOfBirth": "2003-07-05",
 *     "phoneNumber": "0876555430"
 * }
 */
