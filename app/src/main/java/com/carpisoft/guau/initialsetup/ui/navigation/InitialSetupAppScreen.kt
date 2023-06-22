package com.carpisoft.guau.initialsetup.ui.navigation

sealed class InitialSetupAppScreen(val route: String) {
    object InitialScreen : InitialSetupAppScreen(route = "InitialScreen")
    object MyVetsScreen : InitialSetupAppScreen(route = "MyVetsScreen")

}