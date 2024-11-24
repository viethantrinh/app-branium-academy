package net.branium.data.model.dto.response.search


data class Page(
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
    val number: Int
)
