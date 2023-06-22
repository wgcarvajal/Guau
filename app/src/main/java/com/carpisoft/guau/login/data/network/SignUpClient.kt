package com.carpisoft.guau.login.data.network

import com.carpisoft.guau.login.data.network.constants.PathsUrl
import com.carpisoft.guau.login.data.network.model.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpClient {
    @POST(PathsUrl.BASIC_REGISTER)
    suspend fun doRegister(@Body signUpRequest: SignUpRequest): Response<Any>
}
