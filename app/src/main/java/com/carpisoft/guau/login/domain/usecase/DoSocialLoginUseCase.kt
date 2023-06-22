package com.carpisoft.guau.login.domain.usecase

import com.carpisoft.guau.commons.domain.model.Resp
import com.carpisoft.guau.login.domain.model.LoginResp
import com.carpisoft.guau.login.domain.model.SocialLoginReq
import com.carpisoft.guau.login.domain.port.SocialLoginPort
import javax.inject.Inject

class DoSocialLoginUseCase @Inject constructor(private val socialLoginPort: SocialLoginPort) {
    suspend operator fun invoke(socialLoginReq: SocialLoginReq): Resp<LoginResp> {
        return socialLoginPort.doSocialLogin(socialLoginReq = socialLoginReq)
    }
}