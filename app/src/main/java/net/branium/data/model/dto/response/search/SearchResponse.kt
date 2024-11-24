package net.branium.data.model.dto.response.search

import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.repository.SearchRepository

data class SearchResponse (
    val links: List<Link>,
    val content: List<CourseResponse>,
    val page: Page
)
