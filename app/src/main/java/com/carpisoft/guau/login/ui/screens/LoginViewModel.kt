package com.carpisoft.guau.login.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpisoft.guau.commons.domain.model.Resp
import com.carpisoft.guau.commons.ui.model.ErrorUi
import com.carpisoft.guau.login.domain.model.LoginReq
import com.carpisoft.guau.login.domain.model.LoginResp
import com.carpisoft.guau.login.domain.model.SocialLoginReq
import com.carpisoft.guau.login.domain.usecase.DoLoginUseCase
import com.carpisoft.guau.login.domain.usecase.DoSocialLoginUseCase
import com.carpisoft.guau.login.domain.usecase.SaveEmailUseCase
import com.carpisoft.guau.login.domain.usecase.SaveNameUseCase
import com.carpisoft.guau.login.domain.usecase.SaveTokenUseCase
import com.carpisoft.guau.login.domain.usecase.ValidateEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val doSocialLoginUseCase: DoSocialLoginUseCase,
    private val doLoginUseCase: DoLoginUseCase,
    private val validateEmailAndPasswordUseCase: ValidateEmailAndPasswordUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val saveNameUseCase: SaveNameUseCase,
    private val saveEmailUseCase: SaveEmailUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _loginEnabled = MutableStateFlow(false)
    val loginEnabled: StateFlow<Boolean> = _loginEnabled

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showErrorDialog = MutableStateFlow(false)
    val showErrorDialog: StateFlow<Boolean> = _showErrorDialog

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess:StateFlow<Boolean> = _loginSuccess

    var error: ErrorUi? = null

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnabled.value = validateEmailAndPasswordUseCase(email = email, password = password)
    }

    fun doLogin() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = doLoginUseCase(LoginReq(_email.value, _password.value))
            processResult(result)
        }
    }

    fun doSocialLogin(token: String, provider: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                doSocialLoginUseCase(SocialLoginReq(socialToken = token, provider = provider))
            processResult(result)
        }
    }
    private suspend fun processResult(result: Resp<LoginResp>){
        if (result.isValid) {
            val response = result.data!!
            saveTokenUseCase(response.authorization)
            saveNameUseCase(response.name)
            saveEmailUseCase(response.email)
            _loginSuccess.value = true
        } else {
            error = ErrorUi(result.error, result.errorCode)
            _showErrorDialog.value = true
        }
        _isLoading.value = false
    }

    fun getErrorUi(): ErrorUi? {
        return error
    }

    fun dismissErrorDialog() {
        _showErrorDialog.value = false
    }

}