package com.example.test

import android.app.Application
import android.util.Log
import com.example.data.AuthRepository
import com.example.di.appModule
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class TestApp : Application() {
    override fun onCreate() {
        Log.d("TestApp", "onCreate()")
        super.onCreate()
        startKoin {
            printLogger()
            modules(
                appModule,
                module { single<AuthRepository> { MockAuthRepositoryImpl() } }
            )
        }
    }
}