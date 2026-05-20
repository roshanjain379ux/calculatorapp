package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.R

class MainActivity : AppCompatActivity() {

    private lateinit var inputText: TextView
    private lateinit var resultText: TextView

    private var currentInput = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText = findViewById(R.id.inputText)
        resultText = findViewById(R.id.resultText)

        val buttonIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9,
            R.id.btnPlus, R.id.btnMinus,
            R.id.btnMultiply, R.id.btnDivide
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener {
                val button = findViewById<Button>(id)
                currentInput += button.text.toString()
                inputText.text = currentInput
            }
        }

        // ALL CLEAR BUTTON
        findViewById<Button>(R.id.btnAC).setOnClickListener {
            currentInput = ""
            inputText.text = ""
            resultText.text = "0"
        }

        // DELETE LAST DIGIT
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            if (currentInput.isNotEmpty()) {
                currentInput = currentInput.dropLast(1)
                inputText.text = currentInput
            }
        }

        // EQUAL BUTTON
        findViewById<Button>(R.id.btnEqual).setOnClickListener {

            try {

                val result = calculate(currentInput)
                resultText.text = result.toString()

            } catch (e: Exception) {

                resultText.text = "Error"
            }
        }
    }

    // SIMPLE CALCULATION FUNCTION
    private fun calculate(expression: String): Double {

        return if (expression.contains("+")) {
            val parts = expression.split("+")
            parts[0].toDouble() + parts[1].toDouble()

        } else if (expression.contains("-")) {
            val parts = expression.split("-")
            parts[0].toDouble() - parts[1].toDouble()

        } else if (expression.contains("*")) {
            val parts = expression.split("*")
            parts[0].toDouble() * parts[1].toDouble()

        } else if (expression.contains("/")) {
            val parts = expression.split("/")
            parts[0].toDouble() / parts[1].toDouble()

        } else {
            expression.toDouble()
        }
    }
}