package com.carpisoft.guau.commons.di

import com.carpisoft.guau.commons.data.network.ChangeUrlInterceptor
import com.carpisoft.guau.commons.data.network.constants.PathsUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(PathsUrl.SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun  provideInterceptor(): ChangeUrlInterceptor {
        return ChangeUrlInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor:ChangeUrlInterceptor):OkHttpClient
    { // The Interceptor is then added to the client
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}