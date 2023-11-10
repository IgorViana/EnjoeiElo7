package com.example.enjoeielo7.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.enjoeielo7.ui.screen.detail.DetailScreen
import com.example.enjoeielo7.ui.screen.login.LoginScreen
import com.example.enjoeielo7.ui.screen.main.MainScreen
import com.example.enjoeielo7.util.FULL_AUTH_URL

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.LoginScreen.name,
        modifier = modifier
    ) {
        composable(NavigationScreens.LoginScreen.name) {
            LoginScreen(
                onGitHubLoginClick = {
                    openWebIntent(navController.context, FULL_AUTH_URL)
                })
        }


        composable(NavigationScreens.MainScreen.name) {
            MainScreen() { owner, repositoryName ->
                navController.navigate(NavigationScreens.DetailScreen.name + "/$owner/$repositoryName")
            }
        }

        composable(NavigationScreens.DetailScreen.name + "/{owner}/{repositoryName}",
            arguments = listOf(navArgument(name = "owner") {
                type = NavType.StringType
                nullable = false
            }, navArgument(name = "repositoryName") {
                type = NavType.StringType
                nullable = false
            }
            )) {
            DetailScreen()
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

