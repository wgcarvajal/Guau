package com.carpisoft.guau.login.data.network.model

data class SocialLoginRequest(
    val socialToken: String,
    val provider: String
)