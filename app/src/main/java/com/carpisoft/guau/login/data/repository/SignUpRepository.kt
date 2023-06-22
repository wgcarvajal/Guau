package com.carpisoft.guau.login.data.repository

import com.carpisoft.guau.commons.domain.model.Resp
import com.carpisoft.guau.login.data.network.service.SignUpService
import com.carpisoft.guau.login.data.network.model.SignUpRequest
import com.carpisoft.guau.login.domain.model.SignUpReq
import com.carpisoft.guau.login.domain.port.SignUpPort
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val service: SignUpService) : SignUpPort {

    override suspend fun doRegister(signUpReq: SignUpReq): Resp<Any> {
        val response =
            service.doRegister(
                SignUpRequest(
                    email = signUpReq.email,
                    password = signUpReq.password,
                    lastName = signUpReq.lastName,
                    name = signUpReq.name
                )
            )
        return Resp(response.isValid, response.error, response.errorCode, null)
    }
}