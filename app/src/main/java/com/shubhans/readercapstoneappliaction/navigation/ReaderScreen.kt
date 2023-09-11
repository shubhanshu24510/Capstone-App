package com.shubhans.readercapstoneappliaction.navigation

enum class ReaderScreen {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    HomeScreen,
    SearchScreen,
    DetailsScreem,
    UpdateScreen,
    ReaderStateScreen;

    companion object{
        fun fromroute(route: String):ReaderScreen
        =when(route?.substringBefore("/")){
            SplashScreen.name ->SplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name ->CreateAccountScreen
            HomeScreen.name ->HomeScreen
            SearchScreen.name ->SearchScreen
            DetailsScreem.name ->DetailsScreem
            UpdateScreen.name ->UpdateScreen
            ReaderStateScreen.name->ReaderStateScreen
            null ->HomeScreen
            else ->throw IllegalArgumentException("Route $route is not recognised")

        }
    }

}