package com.carpisoft.guau.login.domain.usecase

import android.util.Patterns
import javax.inject.Inject

class ValidateNameAndLastNameAndEmailAndPasswordUseCase @Inject constructor() {
    operator fun invoke(name:String,lastName:String,email:String,password:String):Boolean
    {
        val mPattern =
            Patterns.EMAIL_ADDRESS
        return name.isNotEmpty() && name.length >= 2 && email.isNotEmpty() && lastName.isNotEmpty() && lastName.length>=2 && mPattern.matcher(email).matches() && password.length >= 6
    }
}