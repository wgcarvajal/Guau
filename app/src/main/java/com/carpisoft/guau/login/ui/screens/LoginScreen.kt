package com.carpisoft.guau.login.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.carpisoft.guau.R
import com.carpisoft.guau.commons.domain.usecase.GetMessageErrorUseCase
import com.carpisoft.guau.commons.ui.model.ErrorUi
import com.carpisoft.guau.commons.ui.screens.buttons.GeneralButton
import com.carpisoft.guau.commons.ui.screens.dialogs.OneButtonDialog
import com.carpisoft.guau.commons.ui.screens.loading.SimpleLoading
import com.carpisoft.guau.commons.ui.screens.text.TextQuestionWithLink
import com.carpisoft.guau.commons.ui.screens.textfields.SimpleTextField
import com.carpisoft.guau.commons.ui.screens.textfields.SimpleTextFieldPassword
import com.carpisoft.guau.commons.ui.utils.LaunchActivities
import com.carpisoft.guau.login.navigation.LoginAppScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    launchActivities: LaunchActivities,
    getMessageErrorUseCase: GetMessageErrorUseCase,
    googleSignInClient:GoogleSignInClient
) {
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val loginEnabled by loginViewModel.loginEnabled.collectAsState()
    val isLoading by loginViewModel.isLoading.collectAsState()
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                    try {
                        val account = task.getResult(ApiException::class.java)
                        if (account != null) {
                            Log.i("token", account.idToken!!)
                            loginViewModel.doSocialLogin(account.idToken!!, "Google")
                        } else {
                            Log.i("wilsiton", "Google sign in failed")
                        }
                    } catch (e: ApiException) {
                        Log.i("wilsiton", e.message ?: "")
                    }
                }
            }
            else{
                Log.i("wilsiton", "Google sign in failed")
            }
        }
    if (isLoading) {
        SimpleLoading()
    } else {
        PortraitScreen(
            email = email,
            password = password,
            enabled = loginEnabled,
            onEmailValueChange = {
                loginViewModel.onLoginChange(email = it, password = password)
            },
            onPasswordValueChange = {
                loginViewModel.onLoginChange(email = email, password = it)
            },
            onClickSignIn = { loginViewModel.doLogin() },
            onClickSignUp = { navController.navigate(LoginAppScreen.SignUpScreen.route) },
            onClickGoogle = { authResultLauncher.launch(googleSignInClient.signInIntent) }
        )
    }
    val showErrorDialog by loginViewModel.showErrorDialog.collectAsState()
    OneButtonDialog(
        show = showErrorDialog,
        message = getMessageErrorUseCase(loginViewModel.getErrorUi() ?: ErrorUi()),
        onDismissRequest = { loginViewModel.dismissErrorDialog() })

    val loginSuccess by loginViewModel.loginSuccess.collectAsState()
    if (loginSuccess) {
        launchActivities.launchInitialSetupActivityAndCloseCurrent()
    }
}

@Composable
fun PortraitScreen(
    email: String,
    password: String,
    enabled: Boolean,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onClickSignIn: () -> Unit,
    onClickSignUp: () -> Unit,
    onClickGoogle: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (row, registerBox, divider) = createRefs()
        Column(modifier = Modifier.constrainAs(row) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(divider.top)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                text = stringResource(id = R.string.app_name),
                fontSize = 36.sp,
            )

            SimpleTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp),
                value = email,
                placeholder = stringResource(id = R.string.enter_your_email),
                label = stringResource(id = R.string.email),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "email icon"
                    )
                },
                onValueChange = onEmailValueChange
            )

            SimpleTextFieldPassword(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp),
                value = password,
                placeholder = stringResource(id = R.string.enter_your_password),
                label = stringResource(id = R.string.password),
                onValueChange = onPasswordValueChange
            )

            GeneralButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp),
                label = stringResource(id = R.string.sign_in),
                enabled = enabled,
                onClick = onClickSignIn
            )
            GeneralButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp),
                label = "Google",
                enabled = true,
                onClick = onClickGoogle
            )
        }
        Divider(modifier = Modifier.constrainAs(divider) {
            bottom.linkTo(registerBox.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        TextQuestionWithLink(
            modifier = Modifier.constrainAs(registerBox) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = stringResource(id = R.string.dont_have_an_account),
            link = "${stringResource(id = R.string.sign_up)}."
        ) {
            onClickSignUp()
        }
    }
}


@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X)
@Composable
private fun Preview() {
    PortraitScreen(
        email = "test@gmail.com",
        password = "hola",
        enabled = true,
        onEmailValueChange = {},
        onPasswordValueChange = {},
        onClickSignIn = {},
        onClickSignUp = {},
        onClickGoogle = {})
}