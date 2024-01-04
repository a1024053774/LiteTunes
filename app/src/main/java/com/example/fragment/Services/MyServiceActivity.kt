package com.example.fragment.Services

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment.R

class MyServiceActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.audio_palyer_activity)

        val btnStart = findViewById<Button>(R.id.service_button_start)
        val btnStop = findViewById<Button>(R.id.service_button_stop)

        btnStart.setOnClickListener {

            val serviceIntent = Intent(this, MyService::class.java)
            startService(serviceIntent)

        }

        val function: (v: View) -> Unit = {
            val serviceIntent = Intent(this, MyService::class.java)
            stopService(serviceIntent)
        }
        btnStop.setOnClickListener(function)
    }
}


