package net.branium.ui.screeen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.response.home.Category

@Composable
fun CategoryCourseItem(category: Category, navigationToCourses: (Category) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.hint_color),
                    shape = CircleShape
                ),
            painter = rememberAsyncImagePainter(category.image),
            contentDescription = "circle category image"
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category.title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircleCategoryItemPreview() {
    CategoryCourseItem(Category("1", "title", "image"), {})
}