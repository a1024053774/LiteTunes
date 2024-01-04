package com.example.fragment.Services


import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment.R

class BinderServiceActivity : AppCompatActivity() {

    private var myService: BinderService ?= null

    private var mConnection : ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, binder: IBinder?) {
            val myBinder = binder as BinderService.LocalBinder
            myService = myBinder.service
            myService?.playMusic()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {

        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.audio_palyer_activity)

        val btnStart = findViewById<Button>(R.id.binderservice_button_start)
        val btnStop = findViewById<Button>(R.id.binderservice_button_stop)


        btnStart.setOnClickListener {

            val serviceIntent = Intent(this, BinderService::class.java)
            bindService(serviceIntent,mConnection, Context.BIND_AUTO_CREATE)
            Toast.makeText(getApplicationContext(), "绑定成功", Toast.LENGTH_SHORT).show()
        }

        btnStop.setOnClickListener {

        }
    }

    override fun onStart() {
        super.onStart()
    }
}

