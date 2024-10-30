package net.branium.ui.screen.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.response.course.CourseResponse
import net.branium.data.model.dto.response.home.TopPick
import net.branium.util.formatToVND

@Composable
fun WishlistItemScreen(course: CourseResponse, onNavigateToDetailCourse: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .width(370.dp)
            .clickable {
                onNavigateToDetailCourse(course.id)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(course.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Box(
            modifier = Modifier
                .width(260.dp)
                .height(56.dp)
                .padding(start = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = course.title,
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Trinh Han",
                    fontSize = 8.sp,
                    lineHeight = 16.sp,
                    style = TextStyle(color = colorResource(id = R.color.color_author)),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = formatToVND(course.price.toDouble()),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = formatToVND(course.discountPrice.toDouble()),
                        fontSize = 10.sp,
                        style = TextStyle(color = colorResource(id = R.color.color_author)),
                        modifier = Modifier.padding(start = 4.dp),
                        textDecoration = TextDecoration.LineThrough
                    )

                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp),
                painter = painterResource(id = R.drawable.icon_favorite_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.color_wishlist_favorite))

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WishlistItemScreenPreview() {
    val course = CourseResponse(1, "Docker and AWS for Java Developer", "", 150000, 900000)
    WishlistItemScreen(course, {})
}