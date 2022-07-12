package uk.co.ayaspace.mage.mainTabbedActivities.alarms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import org.w3c.dom.Text
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.model.Alarm
import uk.co.ayaspace.mage.utils.AlarmData
import uk.co.ayaspace.mage.utils.DataAccess

class NewAlarm : AppCompatActivity() {
    lateinit var dataAccess: DataAccess
    lateinit var alarmLabel: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_alarm)
        dataAccess = DataAccess(this)

        alarmLabel = findViewById(R.id.alarm_name_entry)

        val timePicker: TimePicker = findViewById(R.id.alarm_time_picker)
        val saveButton: Button = findViewById<Button>(R.id.new_alarm_save_button)
        saveButton.setOnClickListener {
            setNewAlarmTime(timePicker.hour, timePicker.minute)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    private fun setNewAlarmTime(hour: Int, min: Int) {
        Toast.makeText(this, "alarm set for $hour:$min", Toast.LENGTH_LONG).show()
        val alarmData : AlarmData = AlarmData(this)
        val alarm : Alarm = Alarm(alarmLabel.text.toString(), hour, min, true, false, 0)
        val alarmID = dataAccess.insertNewAlarm(alarm)
        alarmData.setAlarm(hour, min, "${alarm.label} has been activated!!")
    }
}