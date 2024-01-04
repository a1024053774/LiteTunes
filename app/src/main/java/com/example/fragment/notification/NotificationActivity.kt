package com.example.myapplication.notification


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.fragment.R



const val TAG = "NotificationSample"
const val NOTIFY_ME_ID = 1

val CHANNEL_ID = "my_channel_01"

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val btnSend = findViewById<Button>(R.id.notify)
        btnSend.setOnClickListener {

            // 创建通道
            createNotificationChannel()
            // 创建通知
            val notification = createNotification()
            // 发送通知
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(NOTIFY_ME_ID, notification)
            Log.i(TAG, "发送通知")
        }


        val btnClear = findViewById<Button>(R.id.cancel)

        btnClear.setOnClickListener {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // 取消显示在通知列表中的指定通知
            notificationManager.cancel(NOTIFY_ME_ID)
            Log.i(TAG, "取消通知")


        }
    }


    // 创建通道函数
    private fun createNotificationChannel() {
        // 创建通道
        val name = "Hello NotificationSample通知通道"
        val descriptionText = "NotificationSample通知通道测试"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        // 创建通道对象NotificationChannel
        val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
        mChannel.description = descriptionText

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 注册通道
        notificationManager.createNotificationChannel(mChannel)

    }

    // 创建通知函数
    private fun createNotification(): Notification {


        //设置单击通知后所打开的详细界面
        val pendingIntent = PendingIntent.getActivity(this, 0,
            Intent(this, ShowNotificationActivity::class.java), PendingIntent.FLAG_IMMUTABLE)
        // 创建通知
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle("通知发送人")
            .setContentText("我是详细的通知")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // 返回通知
        return builder.build()
    }
}

