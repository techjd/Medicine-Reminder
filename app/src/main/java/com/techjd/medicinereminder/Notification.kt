package com.techjd.medicinereminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notification {
    fun forNotification(CHANNEL_ID: String, notificationID: Int ,context: Context, title: String, contentText: String) {

//        val CHANNEL_ID: String = "1"
//        val notificationID: Int = 1


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Noti1"
            val descriptionText = "Description of Notification"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(contentText)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(contentText)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)



        with(NotificationManagerCompat.from(context)) {
            notify(notificationID, builder.build())
        }


    }
}