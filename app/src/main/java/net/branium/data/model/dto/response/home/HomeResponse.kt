package net.branium.data.model.dto.response.home


data class HomeResponse (
    val cartQuantities: Int,
    val popularCourses: List<PopularCourse>,
    val categories: List<Category>,
    val topPicks: List<TopPick>
)