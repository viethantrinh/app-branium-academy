package net.branium.data.retrofit.service

import net.branium.data.model.dto.request.user.UserInfoRequest
import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.user.ImageResponse
import net.branium.data.model.dto.response.user.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApiService {
    @GET(value = "users/info")
    suspend fun getStudentInfo() : Response<ApiResponse<UserInfoResponse>>

    @PUT(value = "users/info")
    suspend fun updateStudentInfo(@Body request: UserInfoRequest)  : Response<ApiResponse<UserInfoResponse>>

    @GET(value = "users/image")
    suspend fun getStudentImage() : Response<ApiResponse<ImageResponse>>

}