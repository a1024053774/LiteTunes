package com.example.fragment.MusicActivity

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment.R
import com.example.fragment.Services.BinderService


//播放状态
private const val PLAYING = 0

//暂停状态
private const val PAUSE = 1

//停止状态
private const val STOP = 2

//空闲状态
private const val IDLE = 3

class ChuanqiActivity : AppCompatActivity() {
    //播放按钮
    private var play: ImageButton? = null

    //暂停按钮
    private var stop: ImageButton? = null

    //播放器对象
    private var mMediaPlayer: MediaPlayer? = null

    //当前状态
    private var state = IDLE

    private var myService: BinderService?= null

    private lateinit var imageView: ImageView

    private var mConnection : ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, binder: IBinder?) {
            val myBinder = binder as BinderService.LocalBinder
            myService = myBinder.service
            myService?.playMusic()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {

        }
    }

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_activity_chuanqi)

        imageView = findViewById(R.id.image_chuanqi)

        // 初始化播放按钮
        play = findViewById(R.id.play)
        play!!.setOnClickListener {
            if (state == PLAYING) {
                pause()
            } else {
                start()
            }
        }

        // 初始化停止按钮
        stop = findViewById(R.id.stop)
        stop!!.setOnClickListener { stop() }

    }

    // 暂停
    private fun pause() {
        mMediaPlayer!!.pause()
        state = PAUSE
        play!!.setImageResource(R.drawable.play)
        imageView.animate().scaleX(0.8f).scaleY(0.8f).setDuration(500).start()
    }

    // 开始
    private fun start() {
        if (state == IDLE || state == STOP) {
            play()
        } else if (state == PAUSE) {
            mMediaPlayer!!.start()
            state = PLAYING
        }
        play!!.setImageResource(R.drawable.pause)
        imageView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start()
    }

    // 停止
    private fun stop() {
        mMediaPlayer!!.stop()
        state = STOP
        play!!.setImageResource(R.drawable.play)
        imageView.animate().scaleX(0.8f).scaleY(0.8f).setDuration(500).start()
    }

    // 播放
    fun play() {
        try {
            if (mMediaPlayer == null || state == STOP) {
                // 创建MediaPlayer对象并设置Listener
                mMediaPlayer = MediaPlayer.create(this, R.raw.chuanqi)
                mMediaPlayer!!.setOnPreparedListener(listener)
                mMediaPlayer!!.setOnCompletionListener {
                    Log.d("111111","播放一遍啦")
                    mMediaPlayer!!.start()
                    state = PLAYING
                }
            } else {
                // 复用MediaPlayer对象
                mMediaPlayer!!.reset()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // MediaPlayer进入prepared状态开始播放
    private val listener = OnPreparedListener {
        mMediaPlayer!!.start()
        state = PLAYING
    }

    override fun onDestroy() {
        super.onDestroy()
        // Activity销毁后，释放播放器资源
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }


}
