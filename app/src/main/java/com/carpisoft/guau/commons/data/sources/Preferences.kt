package com.carpisoft.guau.commons.data.sources

interface Preferences {
    suspend fun saveBoolean(key:String,value:Boolean)
    suspend fun saveInt(key:String,value:Int)
    suspend fun saveString(key:String,value:String)

    suspend fun getBoolean(key:String):Boolean
    suspend fun getInt(key:String):Int
    suspend fun getString(key:String):String
}