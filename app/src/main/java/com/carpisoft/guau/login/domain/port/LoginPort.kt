package com.carpisoft.guau.login.domain.port

import com.carpisoft.guau.login.domain.model.LoginReq
import com.carpisoft.guau.login.domain.model.LoginResp

interface LoginPort {
    suspend fun doLogin(loginReq: LoginReq):LoginResp
}