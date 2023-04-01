package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var showBefore: TextView
    private lateinit var showAfter: TextView
    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonAC: Button
    private lateinit var buttonComma: Button
    private lateinit var buttonDivision: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonPercent: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonPoM: Button
    private lateinit var equalButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Show text
        showBefore = findViewById(R.id.Show_calculation_Before)
        showAfter = findViewById(R.id.Show_calculation_After)
        //number button
        button0 = findViewById(R.id.materialButtonNumber0)
        button0.onclickButton()
        button1 = findViewById(R.id.materialButtonNumber1)
        button1.onclickButton()
        button2= findViewById(R.id.materialButtonNumber2)
        button2.onclickButton()
        button3 = findViewById(R.id.materialButtonNumber3)
        button3.onclickButton()
        button4 = findViewById(R.id.materialButtonNumber4)
        button4.onclickButton()
        button5 = findViewById(R.id.materialButtonNumber5)
        button5.onclickButton()
        button6 = findViewById(R.id.materialButtonNumber6)
        button6.onclickButton()
        button7 = findViewById(R.id.materialButtonNumber7)
        button7.onclickButton()
        button8 = findViewById(R.id.materialButtonNumber8)
        button8.onclickButton()
        button9 = findViewById(R.id.materialButtonNumber9)
        button9.onclickButton()
        //function button
        buttonPlus = findViewById(R.id.materialButtonPlus)
        buttonPlus.onclickButton()
        buttonMinus = findViewById(R.id.materialButtonMinus)
        buttonMinus.onclickButton()
        buttonMultiply = findViewById(R.id.materialButtonMultiply)
        buttonMultiply.onclickButton()
        buttonDivision = findViewById(R.id.materialButtonDivision)
        buttonDivision.onclickButton()
        buttonComma = findViewById(R.id.materialButtonComma)
        buttonComma.onclickButton()
        buttonPercent = findViewById(R.id.materialButtonPercent)
        buttonPercent.onclickButton()
        equalButton = findViewById(R.id.materialButtonEquals)
        equalButton.onclickButton()
        buttonAC = findViewById(R.id.materialButtonTextAC)
        buttonAC.onclickButton()
        buttonPoM = findViewById(R.id.materialButtonPlusOrMinus)
        buttonPoM.checkPlusOrMinus()
    }
    fun splitStringToCalculation(str: String): List<String> {
        val regex = "([+-]?\\d+(\\.\\d+)?)|[\\+\\-×÷%]".toRegex()
        val listString = regex.findAll(str).map { it.value }.toList()
        return listString
    }

    fun calculate(str: String): Double {
        var listStr: MutableList<String> = splitStringToCalculation(str).toMutableList()
        var sumValue: Double = 0.0
        var regex = "^[.+-×÷]$".toRegex()
        if(listStr.size==1){
            if (listStr[0].matches(regex)){
                sumValue = 0.0
                return sumValue
            }
            sumValue = listStr[0].toDouble()
            return sumValue
        }else if(listStr.size==0){
            sumValue = 0.0
            return sumValue
        }
        var i=0
        println(listStr.size)
        while (i < listStr.size-1 ){
            if(listStr[i]=="×" ){
                if(listStr[i+1].matches(regex)){
                    listStr[i+1]= "1"
                }
                sumValue = listStr[i-1].toDouble() * listStr[i+1].toDouble()
                listStr.removeAt(i-1)
                listStr.removeAt(i-1)
                listStr.removeAt(i-1)
                listStr.add(i-1, sumValue.toString())

            }else if(listStr[i]=="÷"){
                if(listStr[i+1].matches(regex)){
                    listStr[i+1]= "1"
                }
                sumValue = listStr[i-1].toDouble() / listStr[i+1].toDouble()
                listStr.removeAt(i-1)
                listStr.removeAt(i-1)
                listStr.removeAt(i-1)
                listStr.add(i-1, sumValue.toString())
            }else{
                i++
            }
        }
        while (listStr.size != 1){
            if(listStr[1].matches(regex)){
                listStr[1]="0"
            }
            sumValue = listStr[0].toDouble() + listStr[1].toDouble()
            listStr.removeAt(1)
            listStr.removeAt(0)
            listStr.add(0, sumValue.toString())
        }
        return sumValue
    }
    fun splitString(): List<String> {
        var str = showBefore.text.toString()
        val regex = "(?<=[-+×÷%])|(?=[-+×÷%])".toRegex()
        val listString = str.split(regex).filter { it.isNotEmpty() }
        return listString
    }
    fun Button.checkPlusOrMinus(){
        setOnClickListener {
            var str: MutableList<String> = splitString().toMutableList()
            var value: String = text.toString()
            if(value.equals("±")) {
                val regex1 = Regex("\\d+(\\.\\d+)?")
                if (str.size == 0 || str[0] == "0") {  // không có gì hoặc =0 thì có thể viết -
                    showBefore.text = "-"
                } else if (str.size == 1 && str[0] == "-") {  //nếu chỉ có - thì xoá -
                    str.removeAt(0)
                    showBefore.text = str.joinToString(separator = "")
                }else if (str.size == 1 && str[str.size-1].matches(regex1)) {  //nếu là só duy nhất thì : 5 thành -5
                    str.add(str.size-1, "-")
                    showBefore.text = str.joinToString(separator = "")
                }else if(str.size ==2 && str[str.size - 2] == "-"){  // nếu -5 thành 5
                    str.removeAt(str.size-2)
                    showBefore.text = str.joinToString(separator = "")
                } else if (str.size >= 2 && str[str.size - 2] != "-" && str[str.size-1].matches(regex1)) {
                    if(str[str.size - 2]=="+"){  // nếu 6+5 thành 6-5
                        str.removeAt(str.size-2)
                        str.add(str.size-1, "-")
                        showBefore.text = str.joinToString(separator = "")
                    }else if(str[str.size - 2]=="÷" || str[str.size - 2]=="×"){
                        str.add(str.size-1, "-")
                        showBefore.text = str.joinToString(separator = "")
                    }
                } else if (str.size >= 3 && str[str.size - 2] == "-" && str[str.size-1].matches(regex1)) {
                    if(str[str.size - 3].matches(regex1)){  // nếu 8-6 thành 8+6
                        str.removeAt(str.size-2)
                        str.add(str.size-1, "+")
                        showBefore.text = str.joinToString(separator = "")
                    }else { // nếu 8x-5 thành 8x5
                        str.removeAt(str.size-2)
                        showBefore.text = str.joinToString(separator = "")
                    }

                }
            }
        }
    }
    fun Button.onclickButton(){
        setOnClickListener {
            val regexSpecial = Regex("^[+×÷,]$")
            val regexSpecial1 = Regex("^[×÷]$")
            var regexSpecial3 = Regex("^(0|[1-9]\\d*)\$")
            var regexSpecial4 = Regex("^\\d*\\.?\\d+\$")
            var value: String = text.toString()
            var str: MutableList<String> = splitString().toMutableList()
            //không được thực hiện +×÷, khi số chưa có gì cả
            if(showBefore.text=="" && value.matches(regexSpecial)){
                showBefore.text = ""
            }else if(value.equals("AC")){  //Khi ấn AC thì show dưới = 0 và show trên trống
                showBefore.text = ""
                showAfter.text =""
            }else if(value.equals("=")){  //Khi ấn = thì show trên = show dưới và show dưới = kết quả
                showAfter.text = showBefore.text
                showBefore.text = calculate(showBefore.text.toString()).toString()
            }else if(value.equals("0")){  //Khi ấn phím 0 mà show = 0 thì vẫn cho = 0
                if(showBefore.text == "0" || showBefore.text == ""){
                    showBefore.text = text.toString()
                }else{
                    showBefore.text = showBefore.text.toString() + text.toString()
                }
            }else if(value.equals("-")) {
                if(str.size == 0){  //nếu không có j thì -
                    showBefore.text = text.toString()
                }else{
                    if (str[str.lastIndex] == "-") { // phím - , nếu liền trước là - thì biến thành xoá: 5- thành 5+
                        str.add(str.lastIndex, "+")
                        str.removeAt(str.lastIndex )
                        showBefore.text = str.joinToString(separator = "")
                    }else if(str[str.lastIndex]=="+"){  // nếu phía truóc là + thì biến thành -: 56+ thành 56-
                        str.add(str.lastIndex, "-")
                        str.removeAt(str.lastIndex )
                        showBefore.text = str.joinToString(separator = "")
                    }else if(str[0]=="0"){   // nếu 0 thì thành -
                        showBefore.text = text.toString()
                    }else{
                        showBefore.text = showBefore.text.toString() + text.toString()
                    }
                }

            }else if(value.matches(regexSpecial1)){ // nếu  ×÷
                val regexSpecial2 = Regex("^[+×÷.-]$")
                if(str[str.lastIndex].matches(regexSpecial2)){ // trước là +×÷,- thì không ấn đc, ×÷
                    showBefore.text = showBefore.text
                }else{
                    showBefore.text = showBefore.text.toString() + text.toString()
                }
            }else if(value.equals(",")){
                if(str[str.lastIndex].matches(regexSpecial3)){
                    showBefore.text = str.joinToString(separator = "") + "."
                }
            }else if(value.equals("%")){
                if(str[str.lastIndex].matches(regexSpecial4)){
                    var percent: Float = str[str.lastIndex].toFloat() /100
                    str.removeAt(str.lastIndex)
                    showBefore.text = str.joinToString(separator = "") + percent.toString()
                }
            }else if(value.equals("+")){
                if(str.size==0){
                    showBefore.text = ""
                }else{
                    if(str[str.size-1] == "+" || str[str.size-1] == "-"){
                        showBefore.text = showBefore.text.toString()
                    }else{
                        showBefore.text = showBefore.text.toString() + text.toString()
                    }
                }
            }else{
                showBefore.text = showBefore.text.toString() + text.toString()
            }
        }
    }
}