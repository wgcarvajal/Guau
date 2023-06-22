package com.carpisoft.guau.login.data.network.service

import com.carpisoft.guau.commons.data.network.model.Response
import com.carpisoft.guau.commons.data.network.model.error.ResponseError
import com.carpisoft.guau.login.data.network.SignUpClient
import com.carpisoft.guau.login.data.network.model.SignUpRequest
import com.google.gson.Gson
import javax.inject.Inject

class SignUpService @Inject constructor(
    private val signUpClient: SignUpClient,
    private val gson: Gson
) {
    suspend fun doRegister(signUpRequest: SignUpRequest): Response<Any> {
        val response = signUpClient.doRegister(signUpRequest)
        return try {
            if (response.isSuccessful) {
                Response(isValid = true, data = null)
            } else {
                val errorBody = response.errorBody()
                var error = ""
                var errorCode: Int? = null
                if (errorBody != null) {
                    val responseError = gson.fromJson(errorBody.string(), ResponseError::class.java)
                    error = responseError.message
                    errorCode = responseError.errorCode
                }
                Response(isValid = false, error = error, errorCode = errorCode)
            }

        } catch (e: Exception) {
            Response(isValid = false, error = e.message, data = null)
        }
    }
}