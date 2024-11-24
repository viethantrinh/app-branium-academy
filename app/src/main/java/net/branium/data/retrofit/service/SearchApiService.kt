package net.branium.data.retrofit.service

import net.branium.data.model.dto.response.base.ApiResponse
import net.branium.data.model.dto.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("courses/search")
    suspend fun getInfoByKeyword(
        @Query("keyword") keyword: String
    ) : Response<ApiResponse<SearchResponse>>
}