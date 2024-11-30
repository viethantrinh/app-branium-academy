package net.branium.data.model.dto.response.search

import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.repository.SearchRepository

data class SearchResponse (
    val links: List<Link> = emptyList(),
    val content: List<CourseResponse> = emptyList(),
    val page: Page = Page()
)
