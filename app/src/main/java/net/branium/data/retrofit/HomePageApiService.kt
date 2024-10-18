package net.branium.data.retrofit

import net.branium.data.model.dto.response.HomePageRespone
import retrofit2.http.GET

interface HomePageApiService {
    @GET("")
    suspend fun getHomePage() : ResultResponse<HomePageRespone>


}