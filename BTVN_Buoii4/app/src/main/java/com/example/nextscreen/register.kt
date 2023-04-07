package com.example.nextscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class register : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var returnlogin: TextView = findViewById(R.id.login11)
        var buttonSignUp: Button = findViewById<Button>(R.id.input_button)
        var user: EditText = findViewById(R.id.inputLogin)
        var pass1: EditText = findViewById(R.id.passwordlogin)
        var pass2: EditText = findViewById(R.id.passwordAgainlogin)
        var warningUser: TextView = findViewById(R.id.waringuser)
        var warningPassW1: TextView = findViewById(R.id.waringpassword)
        var warningPassW2: TextView = findViewById(R.id.waringpassword2)

        returnlogin.setOnClickListener {
            val itent1 = Intent(this, login:: class.java)
            startActivity(itent1)
            finish()
        }
        buttonSignUp.setOnClickListener {
            var str1: String = checkUser(user.text.toString(), warningUser).toString()
            var str2: String = checkPassWord(pass1.text.toString(), warningPassW1).toString()
            if(str1=="true" && str2 =="true"){
                if(checkTrue(pass1.text.toString(), pass2.text.toString(), warningPassW2) == true){
                    val itent = Intent(this, home:: class.java)
                    itent.putExtra("username", user.text.toString())
                    itent.putExtra("password", pass2.text.toString())
                    startActivity(itent)
                    finish()
                }
            }
        }
    }

    fun checkTrue(str1: String, str2: String, t:TextView): Boolean {
        if(str1 == str2){
            return true
        }else{
            t.text ="*Password nhập lại chưa khớp"
            return false
        }

    }
    fun checkUser(str: String, t: TextView): Boolean {
        var reg = Regex("^(?=.*[A-Z]).{6,}$")
        if(str.matches(reg)){
            t.text=""
            return true
        }else{
            t.text = "*Cần ít nhất 1 chữ cái in hoa và 6 ký tự trở lên"
            return false
        }

    }
    fun checkPassWord(str: String, t: TextView): Boolean {
        /*var reg = Regex(""""^(?=.*[A-Z])(?=.*\\\\d)(?=.*[@\\\$!%*?&])[A-Za-z\\\\d@\\\$!%*?&]{8,}\$""")*/
        var reg = Regex("""^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$""")
        if(str.matches(reg)){
            t.text=""
            return true
        }else{
            t.text = "*Gồm chữ cái in hoa,số, ký tự đặc biệt, từ 8 ký tự trở lên"
            return false
        }

    }
}