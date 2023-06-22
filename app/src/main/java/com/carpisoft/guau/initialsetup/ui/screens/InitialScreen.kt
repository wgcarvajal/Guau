package com.carpisoft.guau.initialsetup.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.carpisoft.guau.R
import com.carpisoft.guau.commons.ui.screens.buttons.GeneralButton
import com.carpisoft.guau.initialsetup.ui.navigation.InitialSetupAppScreen
import com.carpisoft.guau.ui.theme.GuauTheme

@Composable
fun InitialScreen(
    navController: NavHostController,
    showNavigation: (Boolean) -> Unit,
    showFloatActionButton: (Boolean, () -> Unit) -> Unit,
    onSetTitle: (String) -> Unit,
    onBack: () -> Unit
) {
    onSetTitle("")
    showNavigation(false)
    showFloatActionButton(false) {}
    BackHandler {
        onBack()
    }
    val myVetsOnClick = {
        navController.navigate(InitialSetupAppScreen.MyVetsScreen.route)
    }
    ScreenPortrait(myVetsOnClick = myVetsOnClick)
}

@Composable
private fun ScreenPortrait(myVetsOnClick: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (buttonContainers) = createRefs()
        Column(modifier = Modifier.constrainAs(buttonContainers) {
            top.linkTo(parent.top)
            start.linkTo(anchor = parent.start, margin = 24.dp)
            end.linkTo(anchor = parent.end, margin = 24.dp)
            bottom.linkTo(anchor = parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent

        }) {
            GeneralButton(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(id = R.string.my_vets),
                enabled = true,
                onClick = myVetsOnClick
            )
            GeneralButton(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(id = R.string.my_jobs),
                enabled = true,
                onClick = {})
        }

    }
}

@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X, widthDp = 320, heightDp = 534)
@Composable
private fun PreviewSmall() {
    GuauTheme {
        ScreenPortrait(myVetsOnClick = {})
    }
}