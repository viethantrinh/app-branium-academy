package net.branium.ui.screen.auth

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.R
import net.branium.data.model.dto.request.auth.SignUpRequest
import net.branium.ui.theme.textFieldColors
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(onNavigateToSignInScreen: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var showPwdEnabled by remember { mutableStateOf(false) }
    var showConfirmPwdEnabled by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val signUpViewModel: SignUpViewModel = hiltViewModel()

    LaunchedEffect(key1 = signUpViewModel.apiResponseState.value) {
        when (val stateValue = signUpViewModel.apiResponseState.value) {
            is ApiResponseState.Succeeded -> {
                onNavigateToSignInScreen()
                Toast.makeText(context, stateValue.message, Toast.LENGTH_SHORT).show()
            }

            is ApiResponseState.Failed -> {
                Toast.makeText(context, stateValue.message, Toast.LENGTH_SHORT).show()
            }

            is ApiResponseState.Processing -> {
                Toast.makeText(context, stateValue.message, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

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
            value = firstName,
            onValueChange = {
                firstName = it
            },
            label = { Text(text = "First name", color = Color.DarkGray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = textFieldColors()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            label = { Text(text = "Last name", color = Color.DarkGray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = textFieldColors()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = { Text(text = "Email", color = Color.DarkGray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = textFieldColors()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                showPwdEnabled = if (it.isNotBlank()) true else !showPwdEnabled
                password = it
            },
            label = { Text(text = "Password", color = Color.DarkGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = textFieldColors(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible)
                    ImageVector.vectorResource(id = R.drawable.icon_visibility_off_24)
                else
                    ImageVector.vectorResource(id = R.drawable.icon_visibility_24)
                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                    enabled = showPwdEnabled
                ) {
                    Icon(imageVector = icon, contentDescription = "password visibility")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                showConfirmPwdEnabled = if (it.isNotBlank()) true else !showConfirmPwdEnabled
                confirmPassword = it

            },
            label = { Text(text = "Confirm password", color = Color.DarkGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = textFieldColors(),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (confirmPasswordVisible)
                    ImageVector.vectorResource(id = R.drawable.icon_visibility_off_24)
                else
                    ImageVector.vectorResource(id = R.drawable.icon_visibility_24)
                IconButton(
                    onClick = { confirmPasswordVisible = !confirmPasswordVisible },
                    enabled = showConfirmPwdEnabled
                ) {
                    Icon(imageVector = icon, contentDescription = "confirm password visibility")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.align(Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "Already have an account?",
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 14.sp
            )
            Text(
                text = "Login",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(color = 0xFFF95E0A),
                    fontSize = 14.sp
                ),
                modifier = Modifier.clickable(onClick = onNavigateToSignInScreen)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val signUpRequest = SignUpRequest(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
                signUpViewModel.signUp(request = signUpRequest)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = "Sign up")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SignUpScreenPreview() {
    SignUpScreen({})
}