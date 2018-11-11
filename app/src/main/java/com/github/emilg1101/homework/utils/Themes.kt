package com.github.emilg1101.homework.utils

import android.content.Context
import android.preference.PreferenceManager
import com.github.emilg1101.homework.R

object Themes {

    fun getTheme(context: Context): Int {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return when (sharedPreferences.getString("list_preference", "theme_blue")) {
            "theme_blue" -> R.style.AppTheme
            "theme_dark" -> R.style.DarkTheme
            "theme_pink" -> R.style.PinkTheme
            else -> R.style.AppTheme
        }
    }
}
