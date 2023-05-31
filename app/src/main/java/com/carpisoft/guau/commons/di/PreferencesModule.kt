package com.carpisoft.guau.commons.di

import android.content.Context
import com.carpisoft.guau.commons.data.sources.Preferences
import com.carpisoft.guau.commons.data.sources.PreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context): Preferences =
        PreferencesImpl(context)

}