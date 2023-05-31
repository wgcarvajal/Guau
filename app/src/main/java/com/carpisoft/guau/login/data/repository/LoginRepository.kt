package com.carpisoft.guau.login.data.repository

import com.carpisoft.guau.login.data.network.LoginService
import com.carpisoft.guau.login.data.network.model.LoginRequest
import com.carpisoft.guau.login.domain.model.LoginReq
import com.carpisoft.guau.login.domain.model.LoginResp
import com.carpisoft.guau.login.domain.port.LoginPort
import javax.inject.Inject

class LoginRepository @Inject constructor(private val service: LoginService):LoginPort {
    override suspend fun doLogin(loginReq: LoginReq): LoginResp {
        val loginResponse = service.doLogin(LoginRequest(email = loginReq.email, password = loginReq.password))
        return LoginResp(authorization = loginResponse.authorization, email = loginResponse.email, name = loginResponse.name)
    }
}