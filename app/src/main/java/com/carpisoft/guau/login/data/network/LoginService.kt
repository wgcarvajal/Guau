package com.carpisoft.guau.login.data.network

import com.carpisoft.guau.login.data.network.model.LoginRequest
import com.carpisoft.guau.login.data.network.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginClient: LoginClient
) {

    suspend fun doLogin(loginRequest: LoginRequest): LoginResponse {
            return try {
                val response = loginClient.doLogin(loginRequest = loginRequest)
                response.body()!!
            }
            catch (e:Exception)
            {
                e.printStackTrace()
                LoginResponse("","","")
            }
    }
}