package com.carpisoft.guau.login.di

import com.carpisoft.guau.login.data.network.LoginClient
import com.carpisoft.guau.login.data.network.SignUpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginClient(retrofit: Retrofit): LoginClient {
        return retrofit.create(LoginClient::class.java)
    }

    @Provides
    @Singleton
    fun provideSignUpClient(retrofit: Retrofit): SignUpClient {
        return retrofit.create(SignUpClient::class.java)
    }
}