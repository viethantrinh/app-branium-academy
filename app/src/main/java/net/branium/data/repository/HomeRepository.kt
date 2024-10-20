package net.branium.data.repository

import net.branium.data.model.dto.response.home.HomeResponse
import net.branium.data.model.dto.response.base.ResultResponse

interface HomeRepository {
    suspend fun getHomePage() : ResultResponse<HomeResponse>
}