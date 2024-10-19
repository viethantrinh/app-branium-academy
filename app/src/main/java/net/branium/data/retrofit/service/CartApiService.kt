package net.branium.data.retrofit.service

import net.branium.data.model.dto.response.ApiResponse
import net.branium.data.model.dto.response.CourseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApiService {

    @GET(value = "carts/items")
    suspend fun getAllCartItems(): Response<ApiResponse<List<CourseResponse>>>

    @POST(value = "carts/items/{id}")
    suspend fun addNewCartItem(@Path(value = "id") courseId: Int): Response<ApiResponse<List<CourseResponse>>>

    @DELETE(value = "carts/items/{id}")
    suspend fun removeCartItem(@Path(value = "id") courseId: Int): Response<ApiResponse<List<CourseResponse>>>

}