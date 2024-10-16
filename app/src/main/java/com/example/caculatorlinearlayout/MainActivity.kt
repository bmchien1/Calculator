package com.example.caculatorlinearlayout

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.caculatorlinearlayout.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView

    // Calculator state variables
    var state: Int = 1  // Track whether inputting first (1) or second operand (2)
    var op: Int = 0     // Operation: 1 = +, 2 = -, 3 = *, 4 = /
    var op1: Int = 0    // First operand
    var op2: Int = 0    // Second operand

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextView for displaying result
        textResult = findViewById(R.id.text_result)

        // Set click listeners for all buttons
        setListeners()
    }

    private fun setListeners() {
        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)

        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnMinus).setOnClickListener(this)
        findViewById<Button>(R.id.btnMulti).setOnClickListener(this)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(this)

        findViewById<Button>(R.id.btnEquals).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)
        findViewById<Button>(R.id.btnDot).setOnClickListener(this)
        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)

            R.id.btnAdd -> setOperation(1)
            R.id.btnMinus -> setOperation(2)
            R.id.btnMulti -> setOperation(3)
            R.id.btnDivide -> setOperation(4)

            R.id.btnEquals -> calculateResult()
            R.id.btnC -> clearAll()
            R.id.btnCE -> clearEntry()
            R.id.btnDot -> addDecimalPoint()
            R.id.btnPlusMinus -> toggleSign()
        }
    }

    private fun addDigit(digit: Int) {
        if (state == 1) {
            op1 = op1 * 10 + digit
            textResult.text = "$op1"
        } else {
            op2 = op2 * 10 + digit
            textResult.text = "$op2"
        }
    }

    private fun setOperation(operation: Int) {
        if (state == 1) {
            state = 2 // Now inputting second operand
            op = operation // Set the current operation
        }
    }

    private fun calculateResult() {
        var result = 0.0
        when (op) {
            1 -> result = op1.toDouble() + op2.toDouble()
            2 -> result = op1.toDouble() - op2.toDouble()
            3 -> result = op1.toDouble() * op2.toDouble()
            4 -> {
                if (op2 != 0) {
                    result = op1.toDouble() / op2.toDouble()
                } else {
                    textResult.text = "Error" // Division by zero
                    return
                }
            }
        }
        textResult.text = result.toString()

        // Reset state for next operation
        state = 1
        op1 = result.toInt() // Store the result for further operations
        op2 = 0
        op = 0
    }

    private fun clearAll() {
        state = 1
        op = 0
        op1 = 0
        op2 = 0
        textResult.text = "0"
    }

    private fun clearEntry() {
        if (state == 1) {
            op1 = 0
        } else {
            op2 = 0
        }
        textResult.text = "0"
    }

    private fun addDecimalPoint() {
        // Handling decimal point logic can be added here if needed
    }

    private fun toggleSign() {
        if (state == 1) {
            op1 = -op1
            textResult.text = "$op1"
        } else {
            op2 = -op2
            textResult.text = "$op2"
        }
    }
}
