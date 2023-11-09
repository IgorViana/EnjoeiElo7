package com.example.enjoeielo7.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Main Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EnjoeiElo7Theme {
        MainScreen()
    }
}