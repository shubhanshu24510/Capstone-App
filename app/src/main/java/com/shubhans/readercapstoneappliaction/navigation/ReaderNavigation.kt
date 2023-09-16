package com.shubhans.readercapstoneappliaction.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shubhans.readercapstoneappliaction.screens.SplashScreen
import com.shubhans.readercapstoneappliaction.screens.home.HomeScreen
import com.shubhans.readercapstoneappliaction.screens.login.LoginScreen
import com.shubhans.readercapstoneappliaction.screens.search.SearchScreen
import com.shubhans.readercapstoneappliaction.screens.stats.ReaderStatsScreen

@Composable
fun NavigationScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreen.HomeScreen
        .name ){
        composable(ReaderScreen.SplashScreen.name){
            SplashScreen(navController=navController)
        }
        composable(ReaderScreen.LoginScreen.name){
            LoginScreen(navController=navController)
        }
        composable(ReaderScreen.HomeScreen.name){
            HomeScreen(navController=navController)
        }
        composable(ReaderScreen.ReaderStateScreen.name){
            ReaderStatsScreen(navController=navController)
        }
        composable(ReaderScreen.SearchScreen.name){
            SearchScreen(navController =navController)
        }
        }
    }


