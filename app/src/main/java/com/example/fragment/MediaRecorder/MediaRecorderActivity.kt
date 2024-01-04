package com.example.fragment.MediaRecorder

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment.R
import java.util.*


const val TAG = "MyAudioRecorder"

//空闲状态
private const val IDLE = 0

//录制状态
private const val RECORDING = 1

class MediaRecorderActivity : AppCompatActivity() {

    //录制文件路径
    private var recFile: String? = null

    //录音器对象
    private var recorder: MediaRecorder? = null

    //播放器对象
    private var player: MediaPlayer? = null

    //播放按钮
    private var record: ImageButton? = null

    //当前状态
    private var state = IDLE
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediarecorder)

        // 请求授权
        checkPermissions()

        // 初始化录音\停止按钮
        record = findViewById(R.id.record)
        record!!.setOnClickListener {
            if (state == IDLE) {
                //如果文件正在播放停止播放
                if (player != null && player!!.isPlaying) {
                    player!!.stop()
                }
                record()
            } else if (state == RECORDING) {
                stop()
            }
        }

    }

    //录制音频文件
    private fun record() {

        val sdCardDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)

        // 获得当前时间
        val now = Date()
        recFile = "${sdCardDir?.path}/${now.time}.mp3"
        try {
            if (recorder == null) recorder = MediaRecorder()
            // 设置输入为麦克风
            recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
            // 设置输出文件格式
            recorder!!.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT)
            // 音频的编码采用AMR
            recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            recorder!!.setOutputFile(recFile)
            recorder!!.prepare()
            recorder!!.start()
            state = RECORDING
        } catch (e: Exception) {
            e.printStackTrace()
        }
        record!!.setImageResource(R.drawable.recorder_stop)
    }

    //停止音频录制
    private fun stop() {
        // 停止录音，释放recorder对象
        if (recorder != null) {
            recorder!!.stop()
            recorder!!.release()
        }
        recorder = null
        state = IDLE
        record!!.setImageResource(R.drawable.record)

        //录制完成之后马上播放
        play(recFile!!)
    }

    // 播放刚刚录制的音频文件
    private fun play(filename: String) {
        if ("" == filename || state == RECORDING) return
        try {
            if (player == null) {
                player = MediaPlayer()
                player!!.setOnPreparedListener(listener)
            } else {
                player!!.reset()
            }
            player!!.setDataSource(filename)
            player!!.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // MediaPlayer进入prepared状态开始播放
    private val listener = OnPreparedListener { player!!.start() }

    override fun onDestroy() {
        super.onDestroy()
        // Activity销毁后，释放播放器和录音器资源
        if (recorder != null) {
            recorder!!.release()
            recorder = null
        }
        if (player != null) {
            player!!.release()
            player = null
        }
    }

    // 核对权限，并请求授权
    fun checkPermissions() {
        // 1、检查是否权限
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            || checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
            || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            // 请求的权限集合
            val permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
            // 2、请求授权，弹出对话框
            requestPermissions(permissions, 0)
        }
    }
}
