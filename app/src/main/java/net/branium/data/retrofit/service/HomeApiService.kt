package net.branium.data.retrofit.service

import net.branium.data.model.dto.response.home.HomeResponse
import net.branium.data.model.dto.response.base.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {
    @GET(value = "mobile/home-page")
    suspend fun getHomePageData(): Response<ApiResponse<HomeResponse>>
}