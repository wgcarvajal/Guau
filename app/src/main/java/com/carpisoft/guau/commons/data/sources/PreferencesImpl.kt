package com.carpisoft.guau.commons.data.sources

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.carpisoft.guau.commons.data.sources.constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

class PreferencesImpl @Inject constructor(private val context: Context) : Preferences {
    override suspend fun saveBoolean(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        savePreference(preferencesKey,value)
    }

    override suspend fun saveInt(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        savePreference(preferencesKey,value)
    }

    override suspend fun saveString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        savePreference(preferencesKey,value)
    }

    private suspend fun <T>savePreference(preferencesKey:androidx.datastore.preferences.core.Preferences.Key<T>,value:T){
        context.dataStore.edit {preferences->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean {
        val preferencesKey = booleanPreferencesKey(key)
        return getPreferences(preferencesKey)?:false
    }

    override suspend fun getInt(key: String): Int {
        val preferencesKey = intPreferencesKey(key)
        return getPreferences(preferencesKey)?:-1
    }

    override suspend fun getString(key: String): String {
        val preferencesKey = stringPreferencesKey(key)
        return getPreferences(preferencesKey).orEmpty()
    }

    private suspend fun <T>getPreferences(preferencesKey:androidx.datastore.preferences.core.Preferences.Key<T>):T?{
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }
}