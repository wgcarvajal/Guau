package com.carpisoft.guau.login.domain.usecase

import android.util.Patterns
import javax.inject.Inject

class ValidateEmailAndPasswordUseCase @Inject constructor(){
    operator fun invoke(email: String, password: String): Boolean {
        val mPattern =
            Patterns.EMAIL_ADDRESS
        return email.isNotEmpty() && mPattern.matcher(email).matches() && password.length >= 3
    }
}