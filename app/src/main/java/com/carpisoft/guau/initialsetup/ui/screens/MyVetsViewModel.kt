package com.carpisoft.guau.initialsetup.ui.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MyVetsViewModel @Inject constructor():ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading:StateFlow<Boolean> = _loading

    private val _loadVets = MutableStateFlow(false)
    val loadVet:StateFlow<Boolean> = _loadVets

    private val _isEmpty = MutableStateFlow(true)
    val isEmpty:StateFlow<Boolean> = _isEmpty

    private val _showError = MutableStateFlow(false)
    val showError:StateFlow<Boolean> = _showError


    fun showMyVets()
    {

    }

}