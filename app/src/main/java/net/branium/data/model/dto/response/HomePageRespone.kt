package net.branium.data.model.dto.response

import net.branium.data.model.dto.homepage.Category
import net.branium.data.model.dto.homepage.PopularCourse
import net.branium.data.model.dto.homepage.TopPick

data class HomePageRespone (
    val popularCourse: List<PopularCourse>,
    val categories: List<Category>,
    val topPicks: List<TopPick>
)