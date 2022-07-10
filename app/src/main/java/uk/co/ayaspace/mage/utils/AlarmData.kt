package uk.co.ayaspace.mage.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmData() {

    var context:Context? = null
    constructor(context:Context) : this() {
        this.context = context
    }


    fun setAlarm(hour: Int, min: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, hour)
        c.set(Calendar.MINUTE, min)
        c.set(Calendar.SECOND, 0)

        val intent: Intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra("payload", "alarm time")
        intent.action = "uk.co.ayaspace.alarm"
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 42, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent)
    }
}