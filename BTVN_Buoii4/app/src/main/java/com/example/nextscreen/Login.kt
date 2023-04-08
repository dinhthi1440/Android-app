package com.example.nextscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.nextscreen.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var myUser: String = "DinhVanThi"
        var myPass: String = "54654611Thi"
        val save = getSharedPreferences("myAcc", MODE_PRIVATE)
        var retunrSignUp: TextView = findViewById(R.id.register)
        var loginButton: Button = findViewById(R.id.input_button)
        var inputTextUser: EditText = findViewById(R.id.inputLogin)
        var inputTextPass: EditText = findViewById(R.id.passwordlogin)
        var warningUse: TextView = findViewById(R.id.waringuser)
        var warningPass: TextView = findViewById(R.id.waringpassword)
        var rememberPass: CheckBox = findViewById(R.id.RememberAcc)
        retunrSignUp.setOnClickListener {
            val itent1 = Intent(this, SignUp:: class.java)
            startActivity(itent1)
            finish()
        }
        loginButton.setOnClickListener {
            if(myUser == inputTextUser.text.toString() && myPass == inputTextPass.text.toString() ){
                if(rememberPass.isChecked){
                    val edit = save.edit()
                    edit.putString("MyUser", inputTextUser.text.toString())
                    edit.putString("MyPass", inputTextPass.text.toString())
                    edit.apply()
                }
                val itent = Intent(this, Home:: class.java)
                itent.putExtra("username", inputTextUser.text.toString())
                itent.putExtra("password", inputTextPass.text.toString())
                startActivity(itent)
                finish()
            }else if(myUser != inputTextUser.text.toString() && myPass == inputTextUser.text.toString()){
                warningPass.text = ""
                warningUse.text = "*Username is incorrect"
            }else if(myPass != inputTextPass.text.toString() && myUser == inputTextUser.text.toString()){
                warningUse.text =""
                warningPass.text = "*Password is incorrect"
            }else{
                warningPass.text = "*Password is incorrect"
                warningUse.text = "*Username is incorrect"
            }
        }
        var userAlready = save.getString("MyUser", null)
        var passAlready = save.getString("MyPass", null)
        if(userAlready != null || passAlready != null){
            inputTextUser.setText(userAlready)
            inputTextPass.setText(passAlready)
            val itent = Intent(this, Home:: class.java)
            itent.putExtra("username", inputTextUser.text.toString())
            itent.putExtra("password", inputTextPass.text.toString())
            startActivity(itent)
            finish()
        }
    }

}