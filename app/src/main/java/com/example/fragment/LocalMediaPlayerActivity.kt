package com.example.fragment

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import java.util.jar.Manifest


//播放状态
private const val PLAYING = 0

//暂停状态
private const val PAUSE = 1

//停止状态
private const val STOP = 2

//空闲状态
private const val IDLE = 3

class LocalMediaPlayerActivity : AppCompatActivity() {
    //播放按钮
    private var play: ImageButton? = null

    //暂停按钮
    private var stop: ImageButton? = null

    //播放器对象
    private var mMediaPlayer: MediaPlayer? = null

    //当前状态
    private var state = IDLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localmediaplayer)
        // 请求授权
        checkPermissions()
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
    }

    // 停止
    private fun stop() {
        mMediaPlayer!!.stop()
        state = STOP
        play!!.setImageResource(R.drawable.play)
    }

    // 播放
    private fun play() {

        val sdCardDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val path = "${sdCardDir?.path}/Enchanted.mp3"
        try {
            if (mMediaPlayer == null || state == STOP) {
                // 创建MediaPlayer对象并设置Listener
                mMediaPlayer = MediaPlayer()
                mMediaPlayer!!.setOnPreparedListener(listener)
            } else {
                // 复用MediaPlayer对象
                mMediaPlayer!!.reset()
            }
            mMediaPlayer!!.setDataSource(path)
            mMediaPlayer!!.prepare()
        } catch (e: java.lang.Exception) {
            Toast.makeText(this, "没有Mp3文件，请先导入", LENGTH_LONG).show()
        }
    }


    // MediaPlayer进入prepared状态开始播放
    private val listener = MediaPlayer.OnPreparedListener {
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

    // 核对权限，并请求授权
    fun checkPermissions() {
        // 1、检查是否权限
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            // 请求的权限集合
            val permissions = arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
            )
            // 2、请求授权，弹出对话框
            requestPermissions(permissions, 0)
        }
    }
}