package com.carpisoft.guau.login.domain.usecase

import com.carpisoft.guau.login.domain.port.LoginAuthorizationPort
import javax.inject.Inject

class SaveEmailUseCase @Inject constructor(private val loginAuthorizationPort: LoginAuthorizationPort) {
    suspend operator fun invoke(email: String) {
        loginAuthorizationPort.saveEmail(email = email)
    }
}