package com.example.nextscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.content.Intent

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                val itent = Intent(this, login:: class.java)
                startActivity(itent)
                finish()
            },
            2000
        )
    }
}