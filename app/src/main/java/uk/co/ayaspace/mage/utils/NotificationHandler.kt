package uk.co.ayaspace.mage.utils

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Builder
import androidx.core.content.ContextCompat.getSystemService
import uk.co.ayaspace.mage.MainActivity
import uk.co.ayaspace.mage.R
import android.app.NotificationChannel
import android.graphics.Color
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService




class NotificationHandler() {
    val NOTIFIYTAG = "new request"

    fun notify(context: Context, message: String, content: String, number: Int) {
        val intent = Intent(context, MainActivity::class.java)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        val NOTIFICATION_CHANNEL_ID: String = "my_channel_id_01"

        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "My Notifications",
            NotificationManager.IMPORTANCE_HIGH
        )

        // Configure the notification channel.
        notificationChannel.description = "Channel description"
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
        notificationChannel.enableVibration(true)
        notificationManager!!.createNotificationChannel(notificationChannel)


        val notificationBuilder: Builder = Builder(context, NOTIFICATION_CHANNEL_ID)

        notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(uk.co.ayaspace.mage.R.drawable.mage_icon_32)
            .setTicker("Hearty365")
//            .setPriority(Notification.PRIORITY_MAX)
            .setContentTitle(message)
            .setContentText(content)
            .setContentInfo("Info")

        notificationManager.notify( /*notification id*/1, notificationBuilder.build())

    }
}
