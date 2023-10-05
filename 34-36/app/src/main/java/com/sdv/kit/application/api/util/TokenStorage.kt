package com.sdv.kit.application.api.util

import android.content.Context
import android.content.SharedPreferences
import com.sdv.kit.application.api.entity.AccessToken

class TokenStorage(private val context: Context) {
    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(TOKEN_PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isTokenSaved(): Boolean =
        sharedPref.getString(TOKEN_PREF_KEY, TOKEN_EMPTY) != TOKEN_EMPTY

    fun saveToken(accessToken: AccessToken): Boolean {
        sharedPref.edit().apply {
            putString(TOKEN_PREF_KEY, accessToken.value)
            apply()
        }
        return true
    }

    fun getAccessToken(): String =
        sharedPref.getString(TOKEN_PREF_KEY, TOKEN_EMPTY)!!

    fun removeToken() {
        sharedPref.edit().apply {
            remove(TOKEN_PREF_KEY)
            apply()
        }
    }

    companion object {
        private const val TOKEN_PREF_NAME = "token_pref"
        private const val TOKEN_PREF_KEY = "access_token"
        private const val TOKEN_EMPTY = "EMPTY_TOKEN"
    }
}