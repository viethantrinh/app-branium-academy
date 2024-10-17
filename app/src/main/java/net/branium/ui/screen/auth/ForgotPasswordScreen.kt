package net.branium.ui.screen.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import net.branium.ui.theme.textFieldColors
import net.branium.viewmodel.ForgotPasswordViewModel
import net.branium.viewmodel.ForgotPasswordViewModel.EmailSentState

@Composable
fun ForgotPasswordScreen(
    onNavigateBackToSignInScreen: () -> Unit,
    onNavigateToCodeResetScreen: (resetEmail: String) -> Unit
) {
    val context = LocalContext.current
    var resetEmail by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var sendCodeBtnEnabled by remember { mutableStateOf(false) }

    val forgotPwdViewModel: ForgotPasswordViewModel = hiltViewModel()

    LaunchedEffect(forgotPwdViewModel.emailSentState.value) {
        when (forgotPwdViewModel.emailSentState.value) {
            is EmailSentState.SentSucceeded -> {
                Toast.makeText(context, forgotPwdViewModel.message.value, Toast.LENGTH_SHORT).show()
                onNavigateToCodeResetScreen(resetEmail)
            }

            is EmailSentState.SentUnSucceeded -> {
                Toast.makeText(context, forgotPwdViewModel.message.value, Toast.LENGTH_SHORT).show()
            }

            else -> {

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 57.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { onNavigateBackToSignInScreen() },
                modifier = Modifier
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(84.dp))
        Text(
            text = "Enter the email which you used to register account to reset password!".uppercase(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = resetEmail,
            onValueChange = {
                resetEmail = it

                if (it.isEmpty()) {
                    isError = true
                    errorMessage = "This field can't be empty."
                    sendCodeBtnEnabled = false
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
                    isError = true
                    errorMessage = "Please enter a valid email address."
                    sendCodeBtnEnabled = false
                } else {
                    isError = false
                    errorMessage = ""
                    sendCodeBtnEnabled = true
                }
            },
            label = { Text(text = "Email", color = Color.DarkGray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = textFieldColors(),
            isError = isError,
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                forgotPwdViewModel.sendResetEmail(resetEmail)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF95E0A),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 8.dp),
            enabled = sendCodeBtnEnabled
        ) {
            Text(text = "Send code")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen({}, {})
}
