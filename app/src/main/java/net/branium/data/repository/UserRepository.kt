package net.branium.data.repository

import net.branium.data.model.dto.request.user.UserInfoRequest
import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.user.ImageResponse
import net.branium.data.model.dto.response.user.UserInfoResponse

interface UserRepository {
    suspend fun getStudentInfo(): ResultResponse<UserInfoResponse>
    suspend fun updateStudentInfo(userInfoRequest: UserInfoRequest) : ResultResponse<UserInfoResponse>
    suspend fun getStudentImage(): ResultResponse<ImageResponse>
}