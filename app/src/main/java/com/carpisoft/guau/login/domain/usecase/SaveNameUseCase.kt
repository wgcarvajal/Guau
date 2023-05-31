package com.carpisoft.guau.login.domain.usecase

import com.carpisoft.guau.login.domain.port.LoginAuthorizationPort
import javax.inject.Inject

class SaveNameUseCase @Inject constructor(private val loginAuthorizationPort: LoginAuthorizationPort) {
    suspend operator fun invoke(name:String)
    {
        loginAuthorizationPort.saveName(name = name)
    }
}