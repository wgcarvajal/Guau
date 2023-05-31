package com.carpisoft.guau.login.domain.usecase

import com.carpisoft.guau.login.domain.port.LoginAuthorizationPort
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val loginAuthorizationPort: LoginAuthorizationPort) {
    suspend operator fun invoke(token:String)
    {
        loginAuthorizationPort.saveToken(token = token)
    }
}