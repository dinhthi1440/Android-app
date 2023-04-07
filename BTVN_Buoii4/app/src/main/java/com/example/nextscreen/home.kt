package com.example.nextscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var showUserName: TextView = findViewById(R.id.showUser)
        var showPassWord: TextView = findViewById(R.id.showPassword)
        val signOut: Button = findViewById<Button>(R.id.signout)

        val userName = intent.getStringExtra("username")
        val passWord = intent.getStringExtra("password")

        showUserName.text= userName
        showPassWord.text = passWord

        signOut.setOnClickListener {
            val itent = Intent(this, login:: class.java)
            startActivity(itent)
            finish()
        }


    }
}