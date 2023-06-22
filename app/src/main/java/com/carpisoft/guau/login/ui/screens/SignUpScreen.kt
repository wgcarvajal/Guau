package com.carpisoft.guau.login.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun SignUpScreen(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel,
    getMessageErrorUseCase: GetMessageErrorUseCase
) {
    val onNameChange: (String) -> Unit = {
        signUpViewModel.nameChange(it)
    }
    val onLastNameChange: (String) -> Unit = {
        signUpViewModel.lastNameChange(it)
    }
    val onEmailChange: (String) -> Unit = {
        signUpViewModel.emailChange(it)
    }
    val onPasswordChange: (String) -> Unit = {
        signUpViewModel.passwordChange(it)
    }
    val onClickLink: () -> Unit = {
        navController.popBackStack()
    }
    val onClick: () -> Unit = {
        signUpViewModel.signUp()
    }
    val name by signUpViewModel.name.collectAsState()
    val lastName by signUpViewModel.lastName.collectAsState()
    val email by signUpViewModel.email.collectAsState()
    val password by signUpViewModel.password.collectAsState()
    val enableButton by signUpViewModel.enableButton.collectAsState()
    val showLoading by signUpViewModel.showLoading.collectAsState()
    if (showLoading) {
        SimpleLoading()
    } else {
        PortraitScreen(
            name = name,
            lastName = lastName,
            email = email,
            password = password,
            enableButton = enableButton,
            onNameChange = onNameChange,
            onLastNameChange = onLastNameChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onClickLink = onClickLink,
            onClick = onClick
        )
    }
    val resetValues by signUpViewModel.resetValues.collectAsState()
    LaunchedEffect(key1 = resetValues)
    {
        if (resetValues) {
            signUpViewModel.resetValues(false)
            signUpViewModel.initValues()
        }
    }

    LaunchedEffect(key1 = 1) {
        if (name.isNotEmpty() || lastName.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || enableButton) {
            signUpViewModel.initValues()
        }
    }

    val showErrorDialog by signUpViewModel.showErrorDialog.collectAsState()
    OneButtonDialog(
        show = showErrorDialog,
        message = getMessageErrorUseCase(
            signUpViewModel.error ?: ErrorUi(),
            if (signUpViewModel.error != null && signUpViewModel.error!!.code != null && signUpViewModel.error!!.code!! == 1062) {
                stringResource(id = R.string.email)
            } else {
                null
            }
        ),
        onDismissRequest = { signUpViewModel.dismissErrorDialog() })

    val showSuccessDialog by signUpViewModel.showSuccessDialog.collectAsState()
    OneButtonDialog(
        show = showSuccessDialog,
        message = stringResource(id = R.string.successful_registration),
        onDismissRequest = { 
            signUpViewModel.dismissSuccessDialog()
            navController.popBackStack()
        })
}

@Composable
fun PortraitScreen(
    name: String,
    lastName: String,
    email: String,
    password: String,
    enableButton: Boolean,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickLink: () -> Unit,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val content = createRef()
        Column(modifier = Modifier.constrainAs(content) {
            top.linkTo(parent.top)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }, horizontalAlignment = Alignment.CenterHorizontally) {

            SimpleTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                placeholder = "",
                label = "${stringResource(id = R.string.name)} *",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                onValueChange = onNameChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            SimpleTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                placeholder = "",
                label = "${stringResource(id = R.string.last_name)} *",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                onValueChange = onLastNameChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            SimpleTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                placeholder = "",
                label = "${stringResource(id = R.string.email)} *",
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
                onValueChange = onEmailChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            SimpleTextFieldPassword(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                placeholder = "",
                label = "${stringResource(id = R.string.password)} *",
                onValueChange = onPasswordChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextQuestionWithLink(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.already_a_member),
                link = stringResource(id = R.string.login),
                onClickLink = onClickLink
            )
            Spacer(modifier = Modifier.height(24.dp))
            GeneralButton(
                modifier = Modifier.width(200.dp),
                label = stringResource(id = R.string.sign_up),
                enabled = enableButton,
                onClick = onClick
            )
        }
    }
}

@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X)
@Composable
private fun Preview() {
    PortraitScreen(
        name = "Wilson Geovanny",
        lastName = "Carvajal Molina",
        email = "Wilnacio@hotmail.com",
        password = "wilson",
        enableButton = true,
        onNameChange = {},
        onLastNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onClickLink = {},
        onClick = {}
    )
}