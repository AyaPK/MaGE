package uk.co.ayaspace.mage.mainTabbedActivities.alarms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.utils.AlarmData

class NewAlarm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_alarm)

        val timePicker: TimePicker = findViewById(R.id.alarm_time_picker)
        val saveButton: Button = findViewById<Button>(R.id.new_alarm_save_button)
        saveButton.setOnClickListener { setNewAlarmTime(timePicker.hour, timePicker.minute); finish() }

    }

    private fun setNewAlarmTime(hour: Int, min: Int) {
        Toast.makeText(this, "alarm set for $hour:$min", Toast.LENGTH_LONG).show()
        val alarmData : AlarmData = AlarmData(this)
        alarmData.setAlarm(hour, min)
    }
}