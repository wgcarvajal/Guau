package com.carpisoft.guau.login.data.network.service

import com.carpisoft.guau.commons.data.network.model.Response
import com.carpisoft.guau.login.data.network.LoginClient
import com.carpisoft.guau.login.data.network.model.LoginRequest
import com.carpisoft.guau.login.data.network.model.LoginResponse
import com.carpisoft.guau.login.data.network.model.SocialLoginRequest
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginClient: LoginClient
) {

    suspend fun doLogin(loginRequest: LoginRequest): Response<LoginResponse> {
        return try {
            val response = loginClient.doLogin(loginRequest = loginRequest)
            processResponse(response)
        } catch (e: Exception) {
            Response(isValid = false, error = e.message, data = null)
        }
    }

    suspend fun doSocialLogin(socialLoginRequest: SocialLoginRequest): Response<LoginResponse> {
        return try {
            val response = loginClient.doSocialLogin(socialLoginRequest = socialLoginRequest)
            processResponse(response)
        } catch (e: Exception) {
            Response(isValid = false, error = e.message, data = null)
        }
    }

    private fun processResponse(response: retrofit2.Response<LoginResponse>): Response<LoginResponse> {
        return if (response.isSuccessful) {
            Response(isValid = true, data = response.body())
        } else {
            val errorBody = response.errorBody()
            var error = ""
            if (errorBody != null) {
                error = errorBody.string()
            }
            Response(isValid = false, error = error)
        }
    }
}