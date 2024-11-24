package net.branium.data.repository

import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.search.SearchResponse


interface SearchRepository {
    suspend fun getInfoByKeyword(query: String): ResultResponse<SearchResponse>
}