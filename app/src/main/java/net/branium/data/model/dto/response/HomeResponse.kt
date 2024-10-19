package net.branium.data.model.dto.response

import net.branium.data.model.dto.home.Category
import net.branium.data.model.dto.home.PopularCourse
import net.branium.data.model.dto.home.TopPick


data class HomeResponse (
    val cartQuantities: Int,
    val popularCourses: List<PopularCourse>,
    val categories: List<Category>,
    val topPicks: List<TopPick>
)