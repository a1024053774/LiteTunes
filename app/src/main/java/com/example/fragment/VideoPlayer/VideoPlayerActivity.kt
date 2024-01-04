package com.example.fragment.VideoPlayer

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment.R


const val TAG = "VideoPlayer"
private lateinit var videoView: VideoView

class VideoPlayerActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videoplayer)
        // 请求授权
        checkPermissions()
        val videoView = findViewById<VideoView>(R.id.videoview)


        val sdCardDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val path = "${sdCardDir?.path}/BlankSpace.mp4"
        val uri = Uri.parse(path)

        // 创建MediaController
        val mc = MediaController(this)
        // 设置VideoView
        videoView.setMediaController(mc)

        videoView.setOnCompletionListener {
            Toast.makeText(this, "播放完成了", Toast.LENGTH_SHORT).show()
        }
        // 设置播放文件路径
        videoView.setVideoURI(uri)
        videoView.start()


    }


    // 核对权限，并请求授权
    private fun checkPermissions() {
        // 1、检查是否权限
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // 请求的权限集合
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
            // 2、请求授权，弹出对话框
            requestPermissions(permissions, 0)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // 检查屏幕的方向：横屏或竖屏
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 当屏幕为横屏时，设置VideoView全屏显示
            val layoutParams = videoView.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            videoView.layoutParams = layoutParams
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // 当屏幕为竖屏时，设置VideoView按原比例显示
            val layoutParams = videoView.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            videoView.layoutParams = layoutParams
        }
    }

}
