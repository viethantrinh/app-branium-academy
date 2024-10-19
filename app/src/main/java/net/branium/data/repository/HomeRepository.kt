package net.branium.data.repository

import net.branium.data.model.dto.response.HomeResponse
import net.branium.data.retrofit.ResultResponse

interface HomeRepository {
    suspend fun getHomePage() : ResultResponse<HomeResponse>
}