package com.example.enjoeielo7.ui.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    wasValidated: Boolean,
    onGitHubLoginClick: () -> Unit,
    onGetTokenWithSuccess: () -> Unit
) {
    val context = LocalContext.current
    val getTokenSuccess = loginViewModel.onGetTokenSuccess.observeAsState()
    if (wasValidated) {
        loginViewModel.getApiToken()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            onClick = { onGitHubLoginClick() },
            shape = RectangleShape
        ) {
            Text(text = "Login com GitHub", modifier = Modifier.padding(8.dp))
        }
    }

    if (getTokenSuccess.value == true) {
        onGetTokenWithSuccess()
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EnjoeiElo7Theme {
        LoginScreen(wasValidated = false, onGetTokenWithSuccess = {}, onGitHubLoginClick = {})
    }
}