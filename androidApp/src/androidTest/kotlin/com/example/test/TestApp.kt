package com.example.test

import android.app.Application
import android.util.Log

class TestApp : Application() {
    override fun onCreate() {
        Log.d("TestApp", "onCreate()")
        super.onCreate()
    }
}