package com.example

import android.app.Application
import android.util.Log
import com.example.di.initKoin

class ExampleApp: Application() {
    override fun onCreate() {
        Log.d("ExampleApp", "onCreate()")
        super.onCreate()
        initKoin()
    }
}