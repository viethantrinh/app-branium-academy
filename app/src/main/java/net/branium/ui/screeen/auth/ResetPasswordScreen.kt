package net.branium.ui.screeen.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.branium.R
import net.branium.ui.theme.textFieldColors
import net.branium.viewmodel.ApiResponseState
import net.branium.viewmodel.ForgotPasswordViewModel
import net.branium.viewmodel.ResetPasswordViewModel

@Composable
fun ResetPasswordScreen(code: String?, email: String?, onNavigateToSignInScreen: () -> Unit) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var showPwdEnabled by remember { mutableStateOf(false) }
    var showConfirmPwdEnabled by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val resetPasswordViewModel: ResetPasswordViewModel = viewModel()

    LaunchedEffect(key1 = resetPasswordViewModel.apiResponseState.value) {
        when (val stateValue = resetPasswordViewModel.apiResponseState.value) {
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
            .padding(start = 24.dp, end = 24.dp, top = 135.dp)
    ) {
        Text(text = "Enter your new password")
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                showPwdEnabled = if (it.isNotBlank()) true else !showPwdEnabled
                password = it
                /* TODO: validate here */
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
                /* TODO: validate here */

            },
            label = { Text(text = "Confirm password", color = Color.DarkGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = textFieldColors(),
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
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (code != null && email != null) {
                    resetPasswordViewModel.resetPassword(code, email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Text(text = "Reset password")
        }
    }
}