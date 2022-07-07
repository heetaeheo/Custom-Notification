package com.example.customnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.customnotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showBtn.setOnClickListener {
            showNotification()
        }

    }

    private fun showNotification() {
        val channelID = "10000"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val contentView = RemoteViews(packageName, R.layout.custom_notification)
        contentView.setTextViewText(R.id.Title,"My Custom Notification Title")
        contentView.setTextViewText(R.id.content,"My Custom Notification Body")


        val builder = NotificationCompat.Builder(applicationContext,channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCustomContentView(contentView)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, "Custom Notification", NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channelID)
        }
        val notification = builder.build()

        notificationManager.notify(1000, notification)
    }
}