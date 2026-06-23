package com.example

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TestActivity", "onCreate()")
        super.onCreate(savedInstanceState)
    }
}
