package uk.co.ayaspace.mage.utils

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.widget.Toast
import uk.co.ayaspace.mage.MainActivity
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.mainTabbedActivities.alarms.AlarmLaunchedActivity
import uk.co.ayaspace.mage.model.Alarm
import java.util.*
import kotlin.collections.ArrayList

class AlarmBroadcastReceiver: BroadcastReceiver() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    lateinit var dataAccess: DataAccess

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1!!.action.equals("uk.co.ayaspace.alarm")) {
            val extras = p1.extras
            val i: Intent = Intent(p0, AlarmLaunchedActivity::class.java)
            i.putExtra("alarmText", extras?.getString("payload"))
            i.putExtra("min", extras?.getInt("min"))
            i.putExtra("hour", extras?.getInt("hour"))
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            p0!!.startActivity(i)
        }
        else if (p1.action.equals("uk.co.ayaspace.notify")) {
            val extras = p1.extras
            val notify = NotificationHandler()
            notify.notify(p0!!, extras?.getString("title")!!, extras.getString("content")!!, 10)
        }
        else if (p1.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val alarmList: ArrayList<Alarm> = dataAccess.getAllAlarms()
            val ad: AlarmData = AlarmData()
            for (alarm in alarmList) {
                if (!alarm.isNotification) {
                    val c = Calendar.getInstance()
                    c.set(Calendar.HOUR_OF_DAY, alarm.hourOrDay)
                    c.set(Calendar.MINUTE, alarm.minuteOrMonth)
                    c.set(Calendar.SECOND, 0)
                    val intent: Intent = Intent(p0, AlarmBroadcastReceiver::class.java)
                    intent.putExtra("payload", alarm.label)
                    intent.putExtra("min", alarm.minuteOrMonth)
                    intent.putExtra("hour", alarm.hourOrDay)
                    intent.action = "uk.co.ayaspace.alarm"
                    val pendingIntent: PendingIntent = PendingIntent.getBroadcast(p0, 42, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
                    val alarmManager = p0!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmManager.set(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
                } else {
                    val HOUR_TO_SHOW_PUSH = 10
                    val alarmManager = p0!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val alarmPendingIntent by lazy {
                        val intent = Intent(p0, BroadcastReceiver::class.java)
                        intent.action = "uk.co.ayaspace.notify"
                        PendingIntent.getBroadcast(p0, 0, intent, 0)
                    }
                    val calendar = GregorianCalendar.getInstance().apply {
                        if (get(Calendar.HOUR_OF_DAY) >= HOUR_TO_SHOW_PUSH) {
                            add(Calendar.DAY_OF_MONTH, 1)
                        }
                        set(Calendar.HOUR_OF_DAY, HOUR_TO_SHOW_PUSH)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                        set(Calendar.DAY_OF_MONTH, alarm.hourOrDay)
                        set(Calendar.MONTH, alarm.minuteOrMonth)
                    }
                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        AlarmManager.INTERVAL_DAY,
                        alarmPendingIntent
                    )
                }
            }
        }
    }
}