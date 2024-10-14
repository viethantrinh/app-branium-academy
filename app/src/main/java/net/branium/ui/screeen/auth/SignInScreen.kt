package net.branium.ui.screeen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.branium.R
import net.branium.ui.theme.textFieldColors


@Composable
fun SignInScreen(
    onNavigateToForgotPasswordScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToSignUpScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 118.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_brand_logo),
            contentDescription = "app's logo",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Email", color = Color.DarkGray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = textFieldColors()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Password", color = Color.DarkGray) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors(),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_visibility_24),
                    contentDescription = "password visibility icon"
                )
            })
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = AnnotatedString(text = "Forgot password?"),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                color = Color(color = 0xFFF95E0A),
                fontSize = 14.sp,
            ),
            modifier = Modifier
                .align(Alignment.End)
                .clickable(onClick = onNavigateToForgotPasswordScreen)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onNavigateToHomeScreen,
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = "Sign in")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "Don’t have an account?",
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 14.sp
            )
            Text(
                text = "Register now",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(color = 0xFFF95E0A),
                    fontSize = 14.sp
                ),
                modifier = Modifier.clickable(onClick = onNavigateToSignUpScreen)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignInScreenPreview() {
    SignInScreen({}, {}, {})
}