package net.branium.data.repository

import net.branium.data.model.dto.response.base.ResultResponse
import net.branium.data.model.dto.response.search.SearchResponse


interface SearchRepository {
    suspend fun getInfoByKeyword(
        keyword: String,
        page: Int? = null,
        size: Int? = null,
        sort: String? = null,
        category: String? = null
    ): ResultResponse<SearchResponse>
}
