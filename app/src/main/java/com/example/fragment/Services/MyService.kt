package com.example.fragment.Services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.fragment.R


class MyService : Service() {

    private var mMediaPlayer: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.v(TAG,"调用MyService-onStartCommand函数... startId= $startId")
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.blank_space)
            mMediaPlayer!!.setOnPreparedListener{
                mMediaPlayer!!.start()
            }
        }else{
            mMediaPlayer!!.reset()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
//        Log.v(TAG,"调用MyService-onDestory函数...")
    }

}