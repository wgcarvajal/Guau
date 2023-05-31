package com.carpisoft.guau.commons.data.network

import okhttp3.Interceptor
import okhttp3.Response

class ChangeUrlInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}