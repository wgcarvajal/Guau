package com.carpisoft.guau.login.domain.usecase

import com.carpisoft.guau.login.domain.model.LoginReq
import com.carpisoft.guau.login.domain.model.LoginResp
import com.carpisoft.guau.login.domain.port.LoginPort
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(private val loginPort: LoginPort) {

    suspend operator fun invoke(loginReq: LoginReq):LoginResp
    {
        return loginPort.doLogin(loginReq = loginReq)
    }
}