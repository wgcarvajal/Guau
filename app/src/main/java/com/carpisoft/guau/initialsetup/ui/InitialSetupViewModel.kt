package com.carpisoft.guau.initialsetup.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpisoft.guau.login.domain.usecase.SaveEmailUseCase
import com.carpisoft.guau.login.domain.usecase.SaveNameUseCase
import com.carpisoft.guau.login.domain.usecase.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialSetupViewModel @Inject constructor(
    private val saveEmailUseCase: SaveEmailUseCase,
    private val saveNameUseCase: SaveNameUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _showSignOffDialog = MutableStateFlow(false)
    val showSignOffDialog: StateFlow<Boolean> = _showSignOffDialog

    private val _successSignOff = MutableStateFlow(false)
    val successSignOff: StateFlow<Boolean> = _successSignOff

    fun setTitle(title: String) {
        _title.value = title
    }

    fun showSignOffDialog(value: Boolean) {
        _showSignOffDialog.value = value
    }

    fun signOff() {
        viewModelScope.launch(Dispatchers.IO) {
            saveTokenUseCase("")
            saveEmailUseCase("")
            saveNameUseCase("")
            _showSignOffDialog.value = false
            _successSignOff.value = true
        }
    }

}