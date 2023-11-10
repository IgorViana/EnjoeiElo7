package com.example.enjoeielo7

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.enjoeielo7.navigation.Navigation
import com.example.enjoeielo7.navigation.NavigationScreens
import com.example.enjoeielo7.ui.screen.login.LoginViewModel
import com.example.enjoeielo7.ui.theme.EnjoeiElo7Theme
import com.example.enjoeielo7.util.sharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var code by sharedPreferences("code")
    private var state by sharedPreferences("state")
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnjoeiElo7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),
                                title = {
                                    Text("Teste Elo 7")
                                }
                            )
                        },
                    ) { paddingValues ->
                        val navController = rememberNavController()
                        Navigation(
                            navController = navController,
                            modifier = Modifier.padding(paddingValues)
                        )

                        val success = viewModel.onGetTokenSuccess.observeAsState(initial = false)
                        if (success.value) {
                            navController.navigate(NavigationScreens.MainScreen.name)
                            viewModel.resetGetToken()
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = intent?.data

        code = uri?.getQueryParameter("code").orEmpty()
        state = uri?.getQueryParameter("state").orEmpty()

        viewModel.getApiToken()
    }


}