package com.carpisoft.guau.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import com.carpisoft.guau.R
import com.carpisoft.guau.commons.ui.textfields.SimpleTextField
import com.carpisoft.guau.commons.ui.textfields.SimpleTextFieldPassword

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    PortraitScreen(
        email = email,
        password = password,
        onEmailValueChange = {
            loginViewModel.onLoginChange(email = it, password = password)
        },
        onPasswordValueChange = {
            loginViewModel.onLoginChange(email = email, password = it)
        }
    )
}

@Composable
fun PortraitScreen(
    email: String,
    password: String,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val row = createRef()
        Column(modifier = Modifier.constrainAs(row) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
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
        }

    }
}

@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X)
@Composable
private fun Preview() {
    PortraitScreen(
        email = "test@gmail.com",
        password = "hola",
        onEmailValueChange = {},
        onPasswordValueChange = {})
}