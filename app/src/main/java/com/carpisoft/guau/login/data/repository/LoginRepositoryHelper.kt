package com.carpisoft.guau.login.data.repository

import com.carpisoft.guau.commons.data.network.model.Response
import com.carpisoft.guau.commons.domain.model.Resp
import com.carpisoft.guau.login.data.network.model.LoginResponse
import com.carpisoft.guau.login.domain.model.LoginResp

open class LoginRepositoryHelper {

    fun processResponse(response: Response<LoginResponse>): Resp<LoginResp> {
        val loginResponse = response.data
        if (loginResponse != null) {
            return Resp(
                response.isValid, response.error, response.errorCode,
                LoginResp(
                    authorization = loginResponse.authorization,
                    email = loginResponse.email,
                    name = loginResponse.name
                )
            )
        }
        return Resp(response.isValid, response.error, response.errorCode, null)
    }
}