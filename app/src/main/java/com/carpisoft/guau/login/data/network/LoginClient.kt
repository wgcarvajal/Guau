package com.carpisoft.guau.login.data.network

import com.carpisoft.guau.login.data.network.constants.PathsUrl
import com.carpisoft.guau.login.data.network.model.LoginRequest
import com.carpisoft.guau.login.data.network.model.LoginResponse
import com.carpisoft.guau.login.data.network.model.SocialLoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginClient {

    @POST(PathsUrl.LOGIN)
    suspend fun doLogin(@Body loginRequest: LoginRequest):Response<LoginResponse>
    @POST(PathsUrl.SOCIAL_LOGIN)
    suspend fun doSocialLogin(@Body socialLoginRequest: SocialLoginRequest):Response<LoginResponse>

}