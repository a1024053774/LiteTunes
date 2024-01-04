package com.example.fragment.Services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.fragment.R

class BinderService : Service() {
    private var mMediaPlayer: MediaPlayer ? = null

    private var mBinder: IBinder = LocalBinder()

    inner class LocalBinder: Binder() {
        val service : BinderService
            get( ) {
                return this@BinderService
            }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
    }

    fun playMusic() {
        if (mMediaPlayer ==null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.blank_space)
            mMediaPlayer!!.setOnPreparedListener {
                mMediaPlayer!!.start()
            }
        }else{
            mMediaPlayer!!.reset()
        }
    }
    fun stopMusic() {
        mMediaPlayer?.stop()
        mMediaPlayer?.release()
        mMediaPlayer = null
    }
}