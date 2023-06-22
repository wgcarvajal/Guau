package com.carpisoft.guau.login.di

import com.carpisoft.guau.login.data.repository.LoginAuthorizationRepository
import com.carpisoft.guau.login.data.repository.LoginRepository
import com.carpisoft.guau.login.data.repository.SignUpRepository
import com.carpisoft.guau.login.data.repository.SocialLoginRepository
import com.carpisoft.guau.login.domain.port.LoginAuthorizationPort
import com.carpisoft.guau.login.domain.port.LoginPort
import com.carpisoft.guau.login.domain.port.SignUpPort
import com.carpisoft.guau.login.domain.port.SocialLoginPort
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginBindModule {

    @Binds
    abstract fun provideSocialLoginRepository(socialLoginRepository: SocialLoginRepository): SocialLoginPort

    @Binds
    abstract fun provideLoginRepository(loginRepository: LoginRepository): LoginPort


    @Binds
    abstract fun provideLoginAuthorizationRepository(loginAuthorizationRepository: LoginAuthorizationRepository): LoginAuthorizationPort

    @Binds
    abstract fun provideSignUpRepository(signUpRepository: SignUpRepository): SignUpPort
}