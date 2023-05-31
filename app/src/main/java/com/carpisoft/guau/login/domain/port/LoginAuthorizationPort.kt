package com.carpisoft.guau.login.domain.port

interface LoginAuthorizationPort {

    suspend fun saveToken(token:String)
    suspend fun saveEmail(email:String)
    suspend fun saveName(name: String)
}