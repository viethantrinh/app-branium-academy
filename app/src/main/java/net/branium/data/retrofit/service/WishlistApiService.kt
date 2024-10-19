package net.branium.data.retrofit.service

import net.branium.data.model.dto.response.ApiResponse
import net.branium.data.model.dto.response.CourseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WishlistApiService {

    @GET(value = "wish-lists/items")
    suspend fun getAllWishlistItems(): Response<ApiResponse<List<CourseResponse>>>

    @POST(value = "wish-lists/items/{id}")
    suspend fun addNewWishlistItem(@Path(value = "id") courseId: Int): Response<ApiResponse<List<CourseResponse>>>

    @DELETE(value = "wish-lists/items/{id}")
    suspend fun removeWishlistItem(@Path(value = "id") courseId: Int): Response<ApiResponse<List<CourseResponse>>>

}