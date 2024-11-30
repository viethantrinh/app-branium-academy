package net.branium.data.retrofit.service

import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApiService {
    @GET(value = "courses/search")
    suspend fun searchCourses(
        @Query("keyword") keyword: String,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("category") category: String? = null
    ): Response<ApiResponse<SearchResponse>>
}