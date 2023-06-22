package com.carpisoft.guau.login.data.repository

import com.carpisoft.guau.commons.domain.model.Resp
import com.carpisoft.guau.login.data.network.service.LoginService
import com.carpisoft.guau.login.data.network.model.LoginRequest
import com.carpisoft.guau.login.domain.model.LoginReq
import com.carpisoft.guau.login.domain.model.LoginResp
import com.carpisoft.guau.login.domain.port.LoginPort
import javax.inject.Inject

class LoginRepository @Inject constructor(private val service: LoginService) : LoginPort,LoginRepositoryHelper() {
    override suspend fun doLogin(loginReq: LoginReq): Resp<LoginResp> {
        val response =
            service.doLogin(LoginRequest(email = loginReq.email, password = loginReq.password))
        return processResponse(response)
    }
}