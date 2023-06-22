package com.carpisoft.guau.login.data.network.model

data class SignUpRequest (
    val name:String,
    val lastName:String,
    val email:String,
    val password:String
)