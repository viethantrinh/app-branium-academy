package net.branium.data.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.branium.data.model.dto.response.ApiResponse
import net.branium.data.model.dto.response.CourseResponse
import net.branium.data.model.dto.response.ErrorResponse
import net.branium.data.model.dto.response.ResultResponse
import net.branium.data.repository.WishlistRepository
import net.branium.data.retrofit.service.WishlistApiService
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class WishlistRepositoryImpl @Inject
constructor(@Named("RetrofitInstanceWithAuthInterceptor") private val retrofitInstanceWithAuthInterceptor: Retrofit) :
    WishlistRepository {

    private val wishApiService: WishlistApiService by lazy {
        retrofitInstanceWithAuthInterceptor.create(WishlistApiService::class.java)
    }

    override suspend fun listWishlistItems(): ResultResponse<List<CourseResponse>> {
        return withContext(Dispatchers.IO) {
            val response = wishApiService.getAllWishlistItems()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    ResultResponse.Success(responseBody.result)
                } else {
                    ResultResponse.Success(emptyList<CourseResponse>())
                }
            } else {
                val responseBody = parseErrorResponse(response)
                if (responseBody != null) {
                    ResultResponse.Error(Exception(responseBody.message))
                } else {
                    ResultResponse.Error(Exception("get all wishlist's items failed!"))
                }
            }
        }
    }

    override suspend fun addWishlistItem(courseId: Int): ResultResponse<List<CourseResponse>> {
        return withContext(Dispatchers.IO) {
            val response = wishApiService.addNewWishlistItem(courseId)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    ResultResponse.Success(responseBody.result)
                } else {
                    ResultResponse.Success(emptyList<CourseResponse>())
                }
            } else {
                val responseBody = parseErrorResponse(response)
                if (responseBody != null) {
                    ResultResponse.Error(Exception(responseBody.message))
                } else {
                    ResultResponse.Error(Exception("add wishlist item failed!"))
                }
            }
        }
    }

    override suspend fun removeWishlistItem(courseId: Int): ResultResponse<List<CourseResponse>> {
        return withContext(Dispatchers.IO) {
            val response = wishApiService.removeWishlistItem(courseId)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    ResultResponse.Success(responseBody.result)
                } else {
                    ResultResponse.Success(emptyList<CourseResponse>())
                }
            } else {
                val responseBody = parseErrorResponse(response)
                if (responseBody != null) {
                    ResultResponse.Error(Exception(responseBody.message))
                } else {
                    ResultResponse.Error(Exception("remove wishlist item failed!"))
                }
            }
        }
    }

    // Function to parse the error body to ErrorResponse object
    private fun parseErrorResponse(response: Response<ApiResponse<List<CourseResponse>>>): ErrorResponse? {
        val converter = retrofitInstanceWithAuthInterceptor
            .responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, arrayOfNulls(0))
        return try {
            response.errorBody()?.let {
                converter.convert(it) // Convert the error body to ErrorResponse
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}