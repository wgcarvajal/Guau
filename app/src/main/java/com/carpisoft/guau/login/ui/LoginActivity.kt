package com.carpisoft.guau.login.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.carpisoft.guau.commons.domain.usecase.GetMessageErrorUseCase
import com.carpisoft.guau.commons.ui.utils.LaunchActivities
import com.carpisoft.guau.login.navigation.LoginNavigation
import com.carpisoft.guau.login.ui.screens.LoginViewModel
import com.carpisoft.guau.login.ui.screens.SignUpViewModel
import com.carpisoft.guau.ui.theme.GuauTheme
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()

    @Inject
    lateinit var getMessageErrorUseCase: GetMessageErrorUseCase
    @Inject
    lateinit var launchActivities: LaunchActivities
    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuauTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    LoginNavigation(
                        navController = navController,
                        loginViewModel = loginViewModel,
                        signUpViewModel = signUpViewModel,
                        launchActivities = launchActivities,
                        getMessageErrorUseCase = getMessageErrorUseCase,
                        googleSignInClient = googleSignInClient
                    )
                }
            }
        }
    }
}