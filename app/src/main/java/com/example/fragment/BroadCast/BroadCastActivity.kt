//package com.example.fragment.BroadCast
//
//import android.content.IntentFilter
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//
//class BroadCastActivity : AppCompatActivity(){
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val intentFilter = IntentFilter()
//        intentFilter.addAction(ACTION_APP_INNER_BROADCAST)
//        registerReceiver(mReceiver,intentFilter)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        unregisterReceiver(mReceiver)
//    }
//}