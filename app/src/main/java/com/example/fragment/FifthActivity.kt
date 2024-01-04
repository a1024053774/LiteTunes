package com.example.fragment

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FifthActivity : AppCompatActivity() {
    var stopbutton:Button?=null
    var startButton:Button? = null
    var textView:TextView?=null
    var isRunning = true
    var mTimer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)
        initView()
    }

    fun initView() {
        stopbutton = findViewById(R.id.stop_timer)
        startButton = findViewById(R.id.start_timer)
        textView  = findViewById(R.id.timer_text)

        stopbutton?.setOnClickListener{
            isRunning = false

        }


        startButton?.setOnClickListener{
            isRunning = true
            var uiDispatcher = Dispatchers.Main
            GlobalScope.launch (uiDispatcher){
                while (isRunning) {
                    delay(1000L)
                    mTimer++
                    val message = "过去了$mTimer 秒"
                    textView?.text = message
                }
            }
        }


    }
}