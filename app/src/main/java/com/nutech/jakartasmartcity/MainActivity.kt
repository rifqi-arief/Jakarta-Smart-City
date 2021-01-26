package com.nutech.jakartasmartcity

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nutech.jakartasmartcity.fragments.KontakFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.frame_main, KontakFragment())
            .commit()
    }
}