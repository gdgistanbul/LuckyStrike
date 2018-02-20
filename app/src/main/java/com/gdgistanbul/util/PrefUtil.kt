package com.gdgistanbul.util

import android.content.Context
import android.preference.PreferenceManager

/**
 * a basic android sharedpreferences implementation.
 */
class PrefUtil {

    companion object {
        fun getString(context: Context, key: String, defaultValue: String): String {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getString(key, defaultValue)
        }

        fun putString(context: Context, key: String, value: String) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            prefs.edit().putString(key, value).apply()
        }
    }

}