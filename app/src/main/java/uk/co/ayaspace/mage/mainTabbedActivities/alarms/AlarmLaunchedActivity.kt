package uk.co.ayaspace.mage.mainTabbedActivities.alarms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import uk.co.ayaspace.mage.R

class AlarmLaunchedActivity : AppCompatActivity() {
    lateinit var stopButton: Button
    lateinit var alarmLabel: TextView
    lateinit var alarmTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_launched)

        alarmLabel = findViewById(R.id.alarm_screen_text)
        alarmLabel.text = intent.getStringExtra("alarmText")

        alarmTime = findViewById(R.id.alarm_screen_time)
        alarmTime.text = "${formatTime(intent.getIntExtra("hour", 0), intent.getIntExtra("min", 0))}"

        stopButton = findViewById(R.id.alarmStopButton)
        stopButton.setOnClickListener {
            finish()
        }
    }

    private fun formatTime(hour: Int, min: Int): String{
        var minString: String = ""+min
        if (minString.length < 2) {
            minString = "0"+minString
        }

        var hourString: String = ""+hour
        if (hourString.length < 2) {
            hourString = "0"+hourString
        }

        return "$hourString:$minString"
    }
}