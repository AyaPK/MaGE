package uk.co.ayaspace.mage.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.widget.Toast
import uk.co.ayaspace.mage.MainActivity
import uk.co.ayaspace.mage.R

class AlarmBroadcastReceiver: BroadcastReceiver() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1!!.action.equals("uk.co.ayaspace.alarm")) {
            val extras = p1.extras
            Toast.makeText(p0, extras?.getString("payload"), Toast.LENGTH_LONG).show()
        }
        else if (p1.action.equals("android.intent.action.BOOT_COMPLETED")) {
            //pass
        }
        else if (p1.action.equals("uk.co.ayaspace.notify")) {
            val extras = p1.extras
            val notify = NotificationHandler()
            notify.notify(p0!!, extras?.getString("payload")!!, 10)
        }
    }
}