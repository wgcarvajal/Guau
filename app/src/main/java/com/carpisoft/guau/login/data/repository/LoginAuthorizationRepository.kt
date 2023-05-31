package com.carpisoft.guau.login.data.repository

import com.carpisoft.guau.commons.data.sources.Preferences
import com.carpisoft.guau.login.data.sources.constants.LoginPreferencesConstants
import com.carpisoft.guau.login.domain.port.LoginAuthorizationPort
import javax.inject.Inject

class LoginAuthorizationRepository @Inject constructor(private val preferences: Preferences) :
    LoginAuthorizationPort {

    override suspend fun saveToken(token: String) {
        preferences.saveString(key = LoginPreferencesConstants.TOKEN_KEY, value = token)
    }

    override suspend fun saveEmail(email: String) {
        preferences.saveString(key = LoginPreferencesConstants.EMAIL_KEY, value = email)
    }

    override suspend fun saveName(name: String) {
        preferences.saveString(key = LoginPreferencesConstants.NAME_KEY, value = name)
    }
}