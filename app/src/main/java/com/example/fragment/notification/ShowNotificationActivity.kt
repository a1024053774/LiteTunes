package com.example.myapplication.notification


import android.app.NotificationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment.R


class ShowNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // 取消显示在通知列表中的指定通知
        mNotificationManager.cancel(NOTIFY_ME_ID)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}

