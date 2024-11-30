package net.branium.data.model.dto.response.search


data class Page(
    val size: Int = -1,
    val totalElements: Int = 0,
    val totalPages: Int = 0,
    val number: Int = 0
)
