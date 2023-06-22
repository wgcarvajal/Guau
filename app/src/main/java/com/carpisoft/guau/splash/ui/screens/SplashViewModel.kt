package com.carpisoft.guau.splash.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpisoft.guau.splash.domain.usecase.IsLoginTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val isLoginTokenUseCase: IsLoginTokenUseCase):ViewModel() {

    private val _launchInitialSetup = MutableStateFlow(false)
    val launchInitialSetup :StateFlow<Boolean> = _launchInitialSetup

    private val _launchLogin = MutableStateFlow(false)
    val launchLogin :StateFlow<Boolean> = _launchLogin

    fun launchView()
    {
        viewModelScope.launch(Dispatchers.IO) {
            if(isLoginTokenUseCase()){
                _launchInitialSetup.value = true
            }else{
                _launchLogin.value = true
            }
        }

    }
}