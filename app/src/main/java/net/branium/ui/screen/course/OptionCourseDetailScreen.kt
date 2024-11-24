package net.branium.ui.screen.course

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.R
import net.branium.data.model.dto.response.course.CourseDetailResponse
import net.branium.data.model.dto.response.course.Lecture
import net.branium.data.model.dto.response.course.Section
import net.branium.util.formatToVND
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.CartViewModel
import net.branium.viewmodel.HomeViewModel
import net.branium.viewmodel.ResponseState
import net.branium.viewmodel.WishlistViewModel

@Composable
fun BuyNowOptionCourseScreen(courseDetail: CourseDetailResponse, homeViewModel: HomeViewModel) {
    val wishlistViewModel: WishlistViewModel = hiltViewModel()
    var isInWishList by remember { mutableStateOf(courseDetail.inWishList) }
    var userTriggeredUpdate by remember { mutableStateOf(false) }


    val cartViewModel: CartViewModel = hiltViewModel()
    var isInCart by remember { mutableStateOf(courseDetail.inCart) }
    var userTriggeredCartUpdate by remember { mutableStateOf(false) }

    // Theo dõi sự thay đổi của courseDetail
    LaunchedEffect(courseDetail) {
        isInWishList = courseDetail.inWishList
    }

    LaunchedEffect(courseDetail) {
        isInCart = courseDetail.inCart
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.student),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Leaner: ${courseDetail.totalStudents}", fontSize = 10.sp,
                fontWeight = FontWeight(400)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),

            ) {
            Row(
                modifier = Modifier.height(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Creator: ", fontSize = 12.sp)
                Text(
                    text = "Trinh Han",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.primary)
                )
            }
            Row(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_update_alt_24),
                    contentDescription = null,
                    Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "last updated: ${courseDetail.updatedAt}",
                    fontSize = 8.sp,
                )
            }
            Row(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_language_24),
                    contentDescription = null,
                    Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Language: Vietnamese",
                    fontSize = 8.sp,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formatToVND(courseDetail.price),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = formatToVND(courseDetail.discountPrice),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = colorResource(id = R.color.primary)
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (isInCart) {
                    cartViewModel.removeCartItem(courseDetail.id, homeViewModel)
                } else {
                    cartViewModel.addCartItem(courseDetail.id, homeViewModel)
                }
                userTriggeredCartUpdate = true
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = if (isInCart) "Remove from Cart" else "Add to Cart")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (isInWishList) {
                    wishlistViewModel.removeWishListItem(courseDetail.id) // Gọi remove nếu đã có trong wishlist
                } else {
                    wishlistViewModel.addWishListItem(courseDetail.id) // Gọi add nếu chưa có
                }
                userTriggeredUpdate = true // Đánh dấu hành động của người dùng
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(size = 8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black,

                ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = if (isInWishList) "Remove from wishlist" else "Add to wishlist")
        }
    }
    LaunchedEffect(key1 = cartViewModel.responseState.value) {
        if (userTriggeredCartUpdate) {
            cartViewModel.responseState.value?.let { state ->
                if (state is ResponseState.Succeeded) {
                    isInCart = !isInCart

                }
                userTriggeredCartUpdate = false
            }
        }
    }

    // Lắng nghe trạng thái cập nhật từ ViewModel
    LaunchedEffect(key1 = wishlistViewModel.apiResponseState.value) {
        if (userTriggeredUpdate) {
            wishlistViewModel.apiResponseState.value?.let { state ->
                if (state is ResponseState.Succeeded) {
                    // Chỉ cập nhật `isInWishList` khi hành động thành công
                    isInWishList = !isInWishList
                    userTriggeredUpdate = false // Đặt lại cờ
                }
            }
        }
    }


}

@Composable
fun EnrollOptionCourseScreen(courseDetail: CourseDetailResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_nav_wish_list_24),
                contentDescription = null,
                tint = if (courseDetail.inWishList) Color(0xFFF95E0A) else Color(
                    0xFF696C70
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),

            ) {
            Row(
                modifier = Modifier.height(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Creator: ", fontSize = 12.sp)
                Text(
                    text = "Trinh Han",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.primary)
                )
            }
            Row(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_update_alt_24),
                    contentDescription = null,
                    Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "last updated: ${courseDetail.updatedAt}",
                    fontSize = 8.sp,
                )
            }
            Row(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_language_24),
                    contentDescription = null,
                    Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Language: Vietnamese",
                    fontSize = 8.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = "Enroll now")
        }
    }
}

@Composable
fun LearnNowOptionCourseScreen(
    courseDetail: CourseDetailResponse,
    onNavigateToCourseVideoScreen: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_nav_wish_list_24),
                contentDescription = null,
                tint = if (courseDetail.inWishList) Color(0xFFF95E0A) else Color(
                    0xFF696C70
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),

            ) {
            Row(
                modifier = Modifier.height(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Creator: ", fontSize = 12.sp)
                Text(
                    text = "Trinh Han",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.primary)
                )
            }
            Row(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_update_alt_24),
                    contentDescription = null,
                    Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "last updated: ${courseDetail.updatedAt}",
                    fontSize = 8.sp,
                )
            }
            Row(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_language_24),
                    contentDescription = null,
                    Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Language: Vietnamese",
                    fontSize = 8.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                onNavigateToCourseVideoScreen(courseDetail.id)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = "Go to course")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OptionCourseDetailScreen() {
//        BuyNowOptionCourseScreen(courseDetail = fakeData)
//    LearnNowOptionCourseScreen(courseDetail = fakeData, {})
//    EnrollOptionCourseScreen(courseDetail = fakeData)
}

sealed class OptionCourseDetailScreen(val option: String) {
    object LearnNowOption : OptionCourseDetailScreen("Learn Now")
    object EnrollOption : OptionCourseDetailScreen("Enroll")
    object BuyNowOption : OptionCourseDetailScreen("Buy Now")
}
