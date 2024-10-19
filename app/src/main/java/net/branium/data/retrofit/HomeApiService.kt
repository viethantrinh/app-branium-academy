package net.branium.data.retrofit

import net.branium.data.model.dto.response.ApiResponse
import net.branium.data.model.dto.response.HomeResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {
    @GET(value = "mobile/home-page")
    suspend fun getHomePageData(): Response<ApiResponse<HomeResponse>>
}