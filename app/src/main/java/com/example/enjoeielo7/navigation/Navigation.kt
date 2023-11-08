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
import com.example.enjoeielo7.util.FULL_AUTH_URL

@Composable
fun Navigation(navController: NavHostController, wasValidated: Boolean) {
    NavHost(navController = navController, startDestination = NavigationScreens.LoginScreen.name) {
        composable(NavigationScreens.LoginScreen.name) {
            LoginScreen(
                wasValidated = wasValidated,
                onGitHubLoginClick = {
                    openWebIntent(navController.context, FULL_AUTH_URL)
                }, onGetTokenWithSuccess = {
                    navController.navigate(NavigationScreens.MainScreen.name)
                })
        }

        composable(NavigationScreens.MainScreen.name) {
            // TODO Create Main Screen
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

