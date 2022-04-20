package com.arum.movieinfo.core

import android.content.Context
import android.content.SharedPreferences

class SharedPreference (val context: Context) {

    private val prefsName = "loginData"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    private val NAME = "name"
    private val IMAGE_URL = "image_url"

    fun saveName(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(NAME, text)
        editor.apply()
    }

    fun saveImage(text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(IMAGE_URL, text)
        editor.apply()
    }

    fun getName(): String? {
        return sharedPref.getString(NAME, null)
    }

    fun getImage(): String? {
        return sharedPref.getString(IMAGE_URL, null)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear().apply()
    }

}