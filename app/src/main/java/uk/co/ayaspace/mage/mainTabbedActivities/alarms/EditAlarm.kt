package uk.co.ayaspace.mage.mainTabbedActivities.alarms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.switchmaterial.SwitchMaterial
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.model.Alarm
import uk.co.ayaspace.mage.utils.DataAccess

class EditAlarm : AppCompatActivity() {
    lateinit var alarmLabel: TextView
    lateinit var timePicker: TimePicker
    lateinit var toggle: SwitchMaterial
    lateinit var saveButton: Button
    lateinit var dataAccess: DataAccess
    lateinit var deleteAlarm: Button
    lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_alarm)
        dataAccess = DataAccess(this)

        val alarmLabelText = intent.getStringExtra("label").toString()
        val hour = intent.getIntExtra("hour", 0)
        val min = intent.getIntExtra("min", 0)
        val daily = intent.getBooleanExtra("daily", false)
        val alarmID = intent.getIntExtra("id", 0)

        alarmLabel = findViewById(R.id.edit_alarm_name_entry)
        timePicker = findViewById(R.id.edit_alarm_time_picker)
        toggle = findViewById(R.id.daily_toggle)
        saveButton = findViewById(R.id.edit_alarm_save_button)
        deleteAlarm = findViewById(R.id.edit_alarm_delete)
        cancelButton = findViewById(R.id.edit_alarm_cancel)

        alarmLabel.text = alarmLabelText
        timePicker.minute = min
        timePicker.hour = hour
        toggle.isChecked = daily

        saveButton.setOnClickListener {
            val alarm: Alarm = Alarm(alarmLabel.text.toString(), timePicker.hour, timePicker.minute, toggle.isChecked, false, alarmID)
            dataAccess.updateAlarm(alarm)
            setResult(RESULT_OK, intent)
            finish()
        }

        deleteAlarm.setOnClickListener {
            dataAccess.deleteAlarm(alarmID)
            setResult(RESULT_OK, intent)
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }

    }
}