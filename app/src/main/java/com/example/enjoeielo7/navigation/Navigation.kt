package com.example.enjoeielo7.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.enjoeielo7.ui.screen.login.LoginScreen
import com.example.enjoeielo7.ui.screen.main.MainScreen
import com.example.enjoeielo7.util.FULL_AUTH_URL

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationScreens.LoginScreen.name) {
        composable(NavigationScreens.LoginScreen.name) {
            LoginScreen(
                onGitHubLoginClick = {
                    openWebIntent(navController.context, FULL_AUTH_URL)
                })
        }

        composable(NavigationScreens.MainScreen.name) {
            MainScreen()
        }

        composable(NavigationScreens.DetailScreen.name) {
            // TODO Create Detail Screen
        }
    }
}

private fun openWebIntent(context: Context, url: String) {
    ContextCompat.startActivity(
        context,
        Intent(Intent.ACTION_VIEW, Uri.parse(url)),
        null
    )
}

