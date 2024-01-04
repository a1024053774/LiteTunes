package com.example.fragment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.graphics.Color
import android.view.MotionEvent
import android.widget.Button



class FourActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var buttonPlus: Button
    lateinit var buttonMinus: Button
    lateinit var buttonDivide: Button
    lateinit var buttonEqual: Button
    lateinit var buttonTimes: Button
    lateinit var operationText: EditText

    var number1: Float = 0.0f
    var currentOperator = ""
    var currentNumber = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)
        setTheme(androidx.constraintlayout.widget.R.style.Theme_AppCompat_Light_NoActionBar)
        editText = findViewById(R.id.number_edit_text)
        buttonPlus = findViewById(R.id.button_plus)
        buttonMinus = findViewById(R.id.button_minus)
        buttonDivide = findViewById(R.id.button_divide)
        buttonEqual = findViewById(R.id.button_equal)
        buttonTimes = findViewById(R.id.button_times)
        operationText = findViewById(R.id.operation_text)

        setButtonTouchListener(buttonPlus, "+")
        setButtonTouchListener(buttonMinus, "-")
        setButtonTouchListener(buttonDivide, "÷")
        setButtonTouchListener(buttonTimes, "×")

    }

    private fun setButtonTouchListener(button: Button, operator: String) {
        button.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    button.textSize = 25f
                    button.setBackgroundColor(Color.parseColor("#663366"))
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    button.textSize = 38f
                    button.setBackgroundColor(Color.parseColor("#FF018786"))
                    operatorClicked(operator)
                }
            }
            true
        }
    }

    @SuppressLint("SetTextI18n")
    fun operationFunction(view: View) {
        when (view.id) {
            R.id.button_one -> {
                numberClicked(1)
            }

            R.id.button_two -> {
                numberClicked(2)
            }

            R.id.button_three -> {
                numberClicked(3)
            }

            R.id.button_four -> {
                numberClicked(4)
            }

            R.id.button_five -> {
                numberClicked(5)
            }

            R.id.button_six -> {
                numberClicked(6)
            }

            R.id.button_seven -> {
                numberClicked(7)
            }

            R.id.button_eight -> {
                numberClicked(8)
            }

            R.id.button_nine -> {
                numberClicked(9)
            }

            R.id.button_zero -> {
                numberClicked(0)
            }

            R.id.button_dot -> {
                dot()
            }

            R.id.button_discard -> {
                editText.setText("")
                operationText.setText("")
                currentNumber = ""
                currentOperator = ""
            }

            R.id.button_plus -> {
                operatorClicked("+")
            }

            R.id.button_minus -> {
                operatorClicked("-")
            }

            R.id.button_divide -> {
                operatorClicked("÷")
            }

            R.id.button_equal -> {
                equalClicked()
            }

            R.id.button_times -> {
                operatorClicked("×")
            }
        }
    }

    private fun numberClicked(number: Int) {
        currentNumber += number
        editText.setText(currentNumber)
        operationText.setText(currentNumber)

    }

    private fun dot() {
        if (!currentNumber.contains(".")) {
            currentNumber += "."
        }
        val operationText = "${operationText.text}."
        this.operationText.setText(operationText)
    }

    private fun operatorClicked(operator: String) {
        if (currentNumber.isNotEmpty()) {
            number1 = currentNumber.toFloat()
            currentOperator = operator
            currentNumber = ""
            operationText.setText(operationText.text.toString() + operator)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun FourActivity.equalClicked() {
        if (currentNumber.isNotEmpty()) {
            val number2 = currentNumber.toFloat()
            var result = 0.0f
            when (currentOperator) {
                "+" -> {
                    result = number1 + number2
                }

                "-" -> {
                    result = number1 - number2
                }

                "×" -> {
                    result = number1 * number2
                }

                "÷" -> {
                    if (number2 != 0.0f) {
                        result = number1 / number2
                    } else {
                        editText.setText("Can't divide by 0")
                        return
                    }
                }
            }
            editText.setText(String.format("%.2f", result))
            currentNumber = ""
            currentOperator = ""
            operationText.setText("")
        }
    }

}


