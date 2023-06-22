package com.carpisoft.guau.commons.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object GeneralModuleActivityScope {
    @Provides
    @ActivityScoped
    fun providesGoogleSignInClient(@ActivityContext context: Context): GoogleSignInClient {
        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("987785305857-mq7o4d7l3pt2ispufecqt09fe271p4c1.apps.googleusercontent.com") // Request id token if you intend to verify google user from your backend server
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context, signInOptions)
    }
}