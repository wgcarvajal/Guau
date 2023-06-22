package com.carpisoft.guau.splash.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.carpisoft.guau.commons.ui.utils.LaunchActivities
import com.carpisoft.guau.splash.ui.screens.SplashScreen
import com.carpisoft.guau.splash.ui.screens.SplashViewModel
import com.carpisoft.guau.ui.theme.GuauTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var launchActivities: LaunchActivities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuauTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen(
                        splashViewModel = splashViewModel,
                        launchActivities = launchActivities
                    )
                }
            }
        }
    }
}