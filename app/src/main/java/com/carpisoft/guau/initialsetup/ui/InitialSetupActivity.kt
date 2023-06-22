package com.carpisoft.guau.initialsetup.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.carpisoft.guau.R
import com.carpisoft.guau.commons.ui.screens.dialogs.TwoButtonDialog
import com.carpisoft.guau.commons.ui.screens.header.HeadScaffold
import com.carpisoft.guau.commons.ui.utils.LaunchActivities
import com.carpisoft.guau.initialsetup.ui.navigation.InitialSetupNavigation
import com.carpisoft.guau.initialsetup.ui.screens.MyVetsViewModel
import com.carpisoft.guau.ui.theme.GuauTheme
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InitialSetupActivity : ComponentActivity() {

    private val initialSetupViewModel: InitialSetupViewModel by viewModels()
    private val myVetsViewModel: MyVetsViewModel by viewModels()

    @Inject
    lateinit var launchActivities: LaunchActivities

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val onSetTitle: (String) -> Unit = {
                initialSetupViewModel.setTitle(it)
            }
            val signOffOnClick: () -> Unit = {
                initialSetupViewModel.showSignOffDialog(true)
            }
            var showExitAlertDialog by rememberSaveable { mutableStateOf(false) }
            val onBack: () -> Unit = {
                showExitAlertDialog = true
            }
            val navController = rememberNavController()
            val title by initialSetupViewModel.title.collectAsState()
            var showNavigation by rememberSaveable { mutableStateOf(false) }
            var showFloatActionButton by rememberSaveable { mutableStateOf(false) }
            var onClickFloatActionButton: () -> Unit = {}
            val showActionNavigation: (Boolean) -> Unit = {
                showNavigation = it
            }
            val showActionFloatActionButton: (Boolean, () -> Unit) -> Unit = { show, action ->
                onClickFloatActionButton = action
                showFloatActionButton = show
            }

            val onBackOnClick:()->Unit = {
                navController.popBackStack()
            }

            val content: @Composable (Modifier) -> Unit = {
                Box(modifier = it) {
                    InitialSetupNavigation(
                        navController = navController,
                        myVetsViewModel = myVetsViewModel,
                        showActionNavigation = showActionNavigation,
                        showFloatActionButton = showActionFloatActionButton,
                        onSetTitle = onSetTitle,
                        onBack = onBack
                    )
                }
            }
            ScreenPortrait(
                title = title,
                titleFontSize=16.sp,
                iconSize = 20.dp,
                showNavigation = showNavigation,
                showFloatActionButton = showFloatActionButton,
                appBarHeight = 40.dp,
                dropdownMenuWidth = 200.dp,
                content = content,
                signOffOnClick = signOffOnClick,
                onClickFloatActionButton = { onClickFloatActionButton() },
                onBackOnClick = onBackOnClick
            )

            val showSignOffDialog by initialSetupViewModel.showSignOffDialog.collectAsState()
            TwoButtonDialog(
                show = showSignOffDialog,
                message = stringResource(id = R.string.are_you_sure_you_want_to_log_out),
                confirmButtonText = stringResource(id = R.string.ok),
                cancelButtonText = stringResource(id = R.string.cancel),
                confirmButton = {
                    initialSetupViewModel.signOff()
                },
                onDismissRequest = {
                    initialSetupViewModel.showSignOffDialog(false)
                })

            val successSignOff by initialSetupViewModel.successSignOff.collectAsState()
            if (successSignOff) {
                googleSignInClient.signOut().addOnCompleteListener {
                    launchActivities.launchSplashActivityAndCloseCurrent()
                }
            }

            TwoButtonDialog(
                show = showExitAlertDialog,
                message = stringResource(id = R.string.are_you_sure_you_want_to_exit_the_application),
                confirmButtonText = stringResource(id = R.string.ok),
                cancelButtonText = stringResource(id = R.string.cancel),
                confirmButton = {
                    showExitAlertDialog = false
                    (context as Activity).finish()
                },
                onDismissRequest = {
                    showExitAlertDialog = false
                })

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenPortrait(
    title: String,
    titleFontSize:TextUnit,
    iconSize:Dp,
    showNavigation: Boolean,
    showFloatActionButton: Boolean,
    appBarHeight: Dp,
    dropdownMenuWidth: Dp,
    content: @Composable (Modifier) -> Unit,
    signOffOnClick: () -> Unit,
    onClickFloatActionButton: () -> Unit,
    onBackOnClick: () -> Unit
) {
    GuauTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                floatingActionButton = if (showFloatActionButton) {
                    {
                        FloatingActionButton(
                            shape = RoundedCornerShape(50),
                            onClick = onClickFloatActionButton
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = "Add FAB",
                                tint = Color.White,
                            )
                        }
                    }
                } else {
                    {}
                },
                topBar = {
                    HeadScaffold(
                        title = title,
                        showNavigation = showNavigation,
                        titleFontSize = titleFontSize,
                        iconSize = iconSize,
                        appBarHeight = appBarHeight,
                        dropdownMenuWidth = dropdownMenuWidth,
                        signOffOnClick = signOffOnClick,
                        onBackOnClick = onBackOnClick
                    )
                }
            ) { contentPadding ->
                content(Modifier.padding(contentPadding))
            }
        }
    }
}

@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X, widthDp = 320, heightDp = 534)
@Composable
private fun PreviewSmall() {
    ScreenPortrait(
        title = "Test",
        titleFontSize = 16.sp,
        iconSize = 20.dp,
        showNavigation = true,
        showFloatActionButton = true,
        appBarHeight = 40.dp,
        dropdownMenuWidth = 200.dp,
        content = {},
        signOffOnClick = {},
        onClickFloatActionButton = {},
        onBackOnClick = {})
}