package com.example.nextscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var showUserName: TextView = findViewById(R.id.showUser)
        var showPassWord: TextView = findViewById(R.id.showPassword)
        val signOut: Button = findViewById<Button>(R.id.signout)
        showUserName.text= intent.getStringExtra("username")
        showPassWord.text = intent.getStringExtra("password")
        signOut.setOnClickListener {
            val edit = getSharedPreferences("myAcc", MODE_PRIVATE).edit()
            edit.clear()
            edit.commit()
            val itent = Intent(this, Login:: class.java)
            startActivity(itent)
            finish()
        }
        //
    }
}