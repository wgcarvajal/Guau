package com.carpisoft.guau.splash.domain.usecase

import com.carpisoft.guau.login.domain.port.LoginAuthorizationPort
import javax.inject.Inject

class IsLoginTokenUseCase @Inject constructor(
    private val loginAuthorizationPort: LoginAuthorizationPort
) {
    suspend operator fun invoke() = loginAuthorizationPort.getToken() != ""
}