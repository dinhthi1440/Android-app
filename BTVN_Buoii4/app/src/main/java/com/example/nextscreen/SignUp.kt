package com.example.nextscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.nextscreen.databinding.ActivityRegisterBinding

class SignUp : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.login11.setOnClickListener {
            val itent1 = Intent(this, Login:: class.java)
            startActivity(itent1)
            finish()
        }
        binding.signUpButton.setOnClickListener {
            val userName: String = checkUser(binding.inputLogin.text.toString(), binding.waringuser).toString()
            val pass1: String = checkPassWord(binding.passwordlogin.text.toString(), binding.waringpassword).toString()
            if(userName=="true" && pass1 =="true"){
                if(checkTrue(binding.passwordlogin.text.toString(), binding.passwordAgainlogin.text.toString(), binding.waringpassword2) == true){
                    val itent = Intent(this, Home:: class.java)
                    itent.putExtra("username", binding.inputLogin.text.toString())
                    itent.putExtra("password", binding.passwordAgainlogin.text.toString())
                    startActivity(itent)
                    finish()
                }
            }
        }
    }

    private fun checkTrue(str1: String, str2: String, t:TextView): Boolean {
        if(str1 == str2){
            return true
        }else{
            t.text ="*Password nhập lại chưa khớp"
            return false
        }

    }
    private fun checkUser(str: String, t: TextView): Boolean {
        val reg = Regex("^(?=.*[A-Z]).{6,}$")
        if(str.matches(reg)){
            t.text=""
            return true
        }else{
            t.text = "*Cần ít nhất 1 chữ cái in hoa và 6 ký tự trở lên"
            return false
        }

    }
    private fun checkPassWord(str: String, t: TextView): Boolean {
        val reg = Regex("""^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$""")
        if(str.matches(reg)){
            t.text=""
            return true
        }else{
            t.text = "*Gồm chữ cái in hoa,số, ký tự đặc biệt, từ 8 ký tự trở lên"
            return false
        }

    }
}