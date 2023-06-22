package com.carpisoft.guau.commons.ui.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.carpisoft.guau.initialsetup.ui.InitialSetupActivity
import com.carpisoft.guau.login.ui.LoginActivity
import com.carpisoft.guau.splash.ui.SplashActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class LaunchActivities @Inject constructor(@ActivityContext private val context: Context) {

    fun launchInitialSetupActivityAndCloseCurrent(){
        context.startActivity(Intent(context,InitialSetupActivity::class.java))
        (context as Activity).finish()
    }

    fun launchLoginActivityAndCloseCurrent(){
        context.startActivity(Intent(context,LoginActivity::class.java))
        (context as Activity).finish()
    }

    fun launchSplashActivityAndCloseCurrent(){
        context.startActivity(Intent(context,SplashActivity::class.java))
        (context as Activity).finish()
    }
}