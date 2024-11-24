package net.branium.ui.screen.account


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import net.branium.R
import net.branium.data.model.dto.response.user.RoleResponse
import net.branium.data.model.dto.response.user.UserInfoResponse

@Preview(showBackground = true)
@Composable
fun AccountScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Phần thông tin người dùng
        UserInfoSection(fakeUserInfoResponse)

        // Danh sách chức năng
        UserOptionsSection()

        // Nút Sign out
        SignOutButton()
    }

}

@Composable
fun UserInfoSection(user: UserInfoResponse) {
    Box {
        Image(
            painter = painterResource(R.drawable.header_image), // Đặt ảnh bìa ở đây
            contentDescription = "Header",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = rememberAsyncImagePainter(model = user.image), // Đặt ảnh đại diện
            contentDescription = "Avatar",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape)
                .align(Alignment.BottomCenter)
                .padding(top = 100.dp)
        )
    }
}

@Composable
fun UserOptionsSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        UserOptionItem(R.drawable.ic_account_box_24, text = "User info")
        UserOptionItem(R.drawable.ic_system_update_alt_24, text = "Update information")
        UserOptionItem(R.drawable.ic_password_24, text = "Change password")
        UserOptionItem(R.drawable.ic_shopping_cart_24, text = "Shopping cart")
    }
}

@Composable
fun UserOptionItem(iconRes: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Thêm xử lý sự kiện ở đây */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text, tint = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_keyboard_arrow_right_24),
            contentDescription = "Arrow",
            tint = Color.Gray
        )
    }
}

@Composable
fun SignOutButton() {
    Button(
        onClick = { /* Xử lý Sign out */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Sign out")
    }
}


val fakeUserInfoResponse = UserInfoResponse(
    id = "4a39e0fe-27a0-4789-8ba2-b7e49677db34",
    email = "hntrnn19@gmail.com",
    firstName = "Linh",
    lastName = "Khanh",
    gender = false,
    dateOfBirth = "2001-10-06",
    image = "https://be-branium-academy-ad1581e47cac.herokuapp.com/branium-academy/api/v1/resources/images/riftfWen",
    phoneNumber = "0987666543",
    roles = listOf(
        RoleResponse(name = "STUDENT")
    )
)
