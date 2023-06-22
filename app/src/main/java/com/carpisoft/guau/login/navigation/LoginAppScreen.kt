package com.carpisoft.guau.login.navigation

sealed class LoginAppScreen(val route: String) {
    object LoginScreen:LoginAppScreen(route = "LoginScreen")
    object SignUpScreen:LoginAppScreen(route = "SignUpScreen")
}
