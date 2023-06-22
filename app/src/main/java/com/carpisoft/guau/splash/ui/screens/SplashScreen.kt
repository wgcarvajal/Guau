package com.carpisoft.guau.splash.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.carpisoft.guau.commons.ui.utils.LaunchActivities
import com.carpisoft.guau.ui.theme.GuauTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(splashViewModel: SplashViewModel, launchActivities: LaunchActivities) {
    ScreenPortrait()
    val launchInitialSetup by splashViewModel.launchInitialSetup.collectAsState()
    if (launchInitialSetup) {
        launchActivities.launchInitialSetupActivityAndCloseCurrent()
    }

    val launchLogin by splashViewModel.launchLogin.collectAsState()
    if (launchLogin) {
        launchActivities.launchLoginActivityAndCloseCurrent()
    }

    LaunchedEffect(key1 = 1){
        delay(2000)
        splashViewModel.launchView()
    }
}

@Composable
private fun ScreenPortrait() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val text = createRef()
        Text(modifier = Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }, text = "GUAU")
    }
}


@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X, widthDp = 320, heightDp = 534)
@Composable
private fun PreviewSmall() {
    GuauTheme {
        ScreenPortrait()
    }
}