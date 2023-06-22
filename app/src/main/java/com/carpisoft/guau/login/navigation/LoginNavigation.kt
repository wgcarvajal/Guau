package com.carpisoft.guau.login.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carpisoft.guau.commons.domain.usecase.GetMessageErrorUseCase
import com.carpisoft.guau.commons.ui.utils.LaunchActivities
import com.carpisoft.guau.login.ui.screens.LoginScreen
import com.carpisoft.guau.login.ui.screens.LoginViewModel
import com.carpisoft.guau.login.ui.screens.SignUpScreen
import com.carpisoft.guau.login.ui.screens.SignUpViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun LoginNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    launchActivities: LaunchActivities,
    getMessageErrorUseCase: GetMessageErrorUseCase,
    googleSignInClient: GoogleSignInClient
) {
    NavHost(
        navController = navController,
        startDestination = LoginAppScreen.LoginScreen.route
    ) {
        composable(route = LoginAppScreen.LoginScreen.route)
        {
            LoginScreen(
                navController = navController,
                loginViewModel = loginViewModel,
                launchActivities = launchActivities,
                getMessageErrorUseCase = getMessageErrorUseCase,
                googleSignInClient = googleSignInClient
            )
        }

        composable(route = LoginAppScreen.SignUpScreen.route)
        {
            SignUpScreen(
                navController = navController,
                signUpViewModel = signUpViewModel,
                getMessageErrorUseCase = getMessageErrorUseCase
            )
        }
    }
}