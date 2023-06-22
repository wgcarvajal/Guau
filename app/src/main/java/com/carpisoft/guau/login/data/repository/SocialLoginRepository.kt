package com.carpisoft.guau.login.data.repository

import com.carpisoft.guau.commons.domain.model.Resp
import com.carpisoft.guau.login.data.network.service.LoginService
import com.carpisoft.guau.login.data.network.model.SocialLoginRequest
import com.carpisoft.guau.login.domain.model.LoginResp
import com.carpisoft.guau.login.domain.model.SocialLoginReq
import com.carpisoft.guau.login.domain.port.SocialLoginPort
import javax.inject.Inject

class SocialLoginRepository @Inject constructor(private val service: LoginService) :
    SocialLoginPort,LoginRepositoryHelper() {

    override suspend fun doSocialLogin(socialLoginReq: SocialLoginReq): Resp<LoginResp> {
        val response =
            service.doSocialLogin(
                SocialLoginRequest(
                    socialToken = socialLoginReq.socialToken,
                    provider = socialLoginReq.provider
                )
            )
        return processResponse(response)
    }
}