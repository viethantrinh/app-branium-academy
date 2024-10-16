package net.branium.ui.screeen.auth

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.branium.viewmodel.CodeResetViewModel

@Composable
fun CodeResetScreen(
    resetEmail: String,
    onNavigateBackToForgotPwdScreen: () -> Unit,
    onNavigateToResetPasswordScreen: (code: String, resetEmail: String) -> Unit
) {
    val codeResetViewModel: CodeResetViewModel = viewModel()
    var otpValues = remember { mutableStateListOf("", "", "", "", "", "") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
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
                onClick = { onNavigateBackToForgotPwdScreen() },
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
        Spacer(modifier = Modifier.height(58.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter the code we sent to $resetEmail",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "We sent a 6-digit code to your email address. Enter that code to reset password",
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 0..5) {
                OutlinedTextField(
                    value = otpValues[i],
                    onValueChange = { value ->
                        if (value.length <= 1) {
                            otpValues[i] = value
                            // Move to the next field when a digit is entered
                            if (value.isNotEmpty() && i < 5) {
                                focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Next)
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(7.dp),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = VisualTransformation.None,
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                // send request to server to check if verifying is oke
                val code = otpValues.joinToString("")
                codeResetViewModel.verifyCode(code = code)
                // if is ok => navigate to this
                if (codeResetViewModel._verifySucceeded) {
                    onNavigateToResetPasswordScreen(code, resetEmail)
                } else {
                    Toast.makeText(context, "Verify unsuccessful! Try again", Toast.LENGTH_SHORT)
                        .show()
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
            Text(text = "Verify")
        }
    }

}
