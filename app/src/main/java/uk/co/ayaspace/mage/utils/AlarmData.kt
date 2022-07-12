package uk.co.ayaspace.mage.utils

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import uk.co.ayaspace.mage.MainActivity
import uk.co.ayaspace.mage.R
import java.util.*

class AlarmData() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    var context:Context? = null
    constructor(context:Context) : this() {
        this.context = context
    }

    fun setAlarm(hour: Int, min: Int, message: String) {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, hour)
        c.set(Calendar.MINUTE, min)
        c.set(Calendar.SECOND, 0)

        val intent: Intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra("payload", message)
        intent.action = "uk.co.ayaspace.alarm"
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 42, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val x = c.time

        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
    }


    fun schedulePushNotifications(day: Int, month: Int) {
        val HOUR_TO_SHOW_PUSH = 10
        val alarmManager = context!!.getSystemService(ALARM_SERVICE) as AlarmManager
        val alarmPendingIntent by lazy {
            val intent = Intent(context, BroadcastReceiver::class.java)
            intent.action = "uk.co.ayaspace.notify"
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }

        val calendar = GregorianCalendar.getInstance().apply {
            if (get(Calendar.HOUR_OF_DAY) >= HOUR_TO_SHOW_PUSH) {
                add(Calendar.DAY_OF_MONTH, 1)
            }

            set(Calendar.HOUR_OF_DAY, HOUR_TO_SHOW_PUSH)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.MONTH, month)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmPendingIntent
        )
    }
}