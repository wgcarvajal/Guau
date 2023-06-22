package com.carpisoft.guau.initialsetup.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carpisoft.guau.initialsetup.ui.screens.InitialScreen
import com.carpisoft.guau.initialsetup.ui.screens.MyVetsScreen
import com.carpisoft.guau.initialsetup.ui.screens.MyVetsViewModel

@Composable
fun InitialSetupNavigation(
    navController: NavHostController,
    myVetsViewModel: MyVetsViewModel,
    showActionNavigation: (Boolean) -> Unit,
    showFloatActionButton: (Boolean, () -> Unit) -> Unit,
    onSetTitle: (String) -> Unit,
    onBack: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = InitialSetupAppScreen.InitialScreen.route
    ) {
        composable(route = InitialSetupAppScreen.InitialScreen.route)
        {
            InitialScreen(
                navController = navController,
                showNavigation = showActionNavigation,
                showFloatActionButton = showFloatActionButton,
                onSetTitle = onSetTitle,
                onBack = onBack
            )
        }
        composable(route = InitialSetupAppScreen.MyVetsScreen.route)
        {
            MyVetsScreen(
                myVetsViewModel = myVetsViewModel,
                showNavigation = showActionNavigation,
                showFloatActionButton = showFloatActionButton,
                onSetTitle = onSetTitle,
            )
        }
    }
}