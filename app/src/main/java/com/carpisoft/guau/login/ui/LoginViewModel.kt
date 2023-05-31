package com.carpisoft.guau.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpisoft.guau.login.domain.model.LoginReq
import com.carpisoft.guau.login.domain.usecase.DoLoginUseCase
import com.carpisoft.guau.login.domain.usecase.ValidateEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val doLoginUseCase: DoLoginUseCase,
    private val validateEmailAndPasswordUseCase: ValidateEmailAndPasswordUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _loginEnabled = MutableStateFlow(false)
    val loginEnabled: StateFlow<Boolean> = _loginEnabled

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnabled.value = validateEmailAndPasswordUseCase(email = email, password = password)
    }

    fun doLogin() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = doLoginUseCase(LoginReq(_email.value, _password.value))
            _isLoading.value = false
        }
    }
}