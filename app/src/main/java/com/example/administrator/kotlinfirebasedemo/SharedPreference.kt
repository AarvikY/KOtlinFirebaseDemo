package com.example.administrator.kotlinfirebasedemo

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPreference {

    lateinit var sharedPreference:SharedPreferences
    lateinit var editor:SharedPreferences.Editor


    fun setValue(context: Context,value:String){
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreference.edit()
        editor.putString("val",value)
        editor.apply()
        editor.commit()
    }

    fun getValue(context: Context) : String?{
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreference.getString("val",null)
    }

}