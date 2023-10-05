package com.sdv.kit.application.util

import android.content.Context

class SharedPrefUtil(context: Context) {
    private val sharedPref = context.getSharedPreferences(PLACES_SHARED_PREF_KEY, Context.MODE_PRIVATE)

    fun incrementFsqIdCount(fsqId: String) {
        val currentValue = sharedPref.getInt(fsqId, 0)
        sharedPref.edit().apply {
            putInt(fsqId, currentValue.inc())
            apply()
        }
    }

    fun getFsqIdCount(fsqId: String): Int = sharedPref.getInt(fsqId, 0)

    fun remove() {
        sharedPref.edit().apply {
            clear()
            apply()
        }
    }

    companion object {
        private const val PLACES_SHARED_PREF_KEY = "places_shared_pref"
    }
}