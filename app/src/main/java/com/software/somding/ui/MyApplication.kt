package com.software.somding.ui

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
	companion object {
		lateinit var prefs: SharedPreferences
	}

	override fun onCreate() {
		super.onCreate()
		prefs = getSharedPreferences("accessToken", Context.MODE_PRIVATE)
	}
}