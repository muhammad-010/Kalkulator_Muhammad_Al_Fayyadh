package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var expression: TextView
    private lateinit var the_result: TextView

    private var firstNum = ""
    private var secondNum = ""
    private var operatorNow = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expression = findViewById(R.id.expression)
        the_result = findViewById(R.id.res)
    }

    fun numClick(view: View) {
        val btn = view as Button
        val num = btn.text.toString()
        if (operatorNow.isEmpty()) {
            firstNum += num
        } else {
            secondNum += num
        }
        updateExpression()
    }

    fun oprClick(view: View) {
        val btn = view as Button
        if (firstNum.isNotEmpty()) {
            operatorNow = btn.text.toString()
            updateExpression()
        }
    }

    fun equalClick(view: View) {
        var equal = 0.0
        val num1 = firstNum.toDoubleOrNull() ?: 0.0
        val num2 = secondNum.toDoubleOrNull() ?: 0.0
        when (operatorNow) {
            "+" -> equal = num1 + num2
            "-" -> equal = num1 - num2
            "×" -> equal = num1 * num2
            "÷" -> {
                if (num2 != 0.0) {
                    equal = num1 / num2
                }
            }
        }
        if (equal % 1 == 0.0) {
            val intResult = equal.toInt()
            the_result.text = intResult.toString()
        } else {
            val decimalFormat = DecimalFormat("#." + "#".repeat(100))
            the_result.text = decimalFormat.format(equal)
        }
    }

    fun clearNumber(view: View) {
        firstNum = ""
        secondNum = ""
        operatorNow = ""
        expression.text = ""
        the_result.text = ""
    }

    private fun updateExpression() {
        expression.text = "$firstNum $operatorNow $secondNum"
    }
}