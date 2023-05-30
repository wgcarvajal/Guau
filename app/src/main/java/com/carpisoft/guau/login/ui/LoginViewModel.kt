package com.carpisoft.guau.login.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel() {

    private val _email = MutableStateFlow("")
    val email:StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password:StateFlow<String> = _password

    fun onLoginChange(email:String,password:String)
    {
        _email.value = email
        _password.value = password
    }
}