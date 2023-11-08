package com.example.enjoeielo7.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.enjoeielo7.ui.screen.login.LoginScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationScreens.LoginScreen.name) {
        composable(NavigationScreens.LoginScreen.name) {
            LoginScreen()
        }

        composable(NavigationScreens.MainScreen.name) {
            // TODO Create Main Screen
        }

        composable(NavigationScreens.DetailScreen.name) {
            // TODO Create Detail Screen
        }
    }
}