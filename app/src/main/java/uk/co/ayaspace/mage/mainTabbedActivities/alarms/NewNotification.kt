package uk.co.ayaspace.mage.mainTabbedActivities.alarms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.model.Alarm
import uk.co.ayaspace.mage.utils.AlarmData
import uk.co.ayaspace.mage.utils.DataAccess

class NewNotification : AppCompatActivity() {
    lateinit var notificationTitle: TextView
    lateinit var notificationText: TextView
    lateinit var notificationDatePicker: DatePicker
    lateinit var savebutton: Button
    lateinit var dataAccess: DataAccess

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notification)
        dataAccess = DataAccess(this)

        notificationTitle = findViewById(R.id.notification_name_entry)
        notificationText = findViewById(R.id.notification_text_entry)
        notificationDatePicker = findViewById(R.id.notification_date_picker)
        savebutton = findViewById(R.id.new_notification_save_button)

        savebutton.setOnClickListener {
            val notification: Alarm = Alarm(notificationTitle.text.toString(), notificationDatePicker.dayOfMonth, notificationDatePicker.month,
                                        daily = false, isNotification = true, 0, notificationText.text.toString())
            dataAccess.insertNewAlarm(notification)
            setResult(RESULT_OK, intent)
            val alarmData : AlarmData = AlarmData(this)
            alarmData.schedulePushNotifications(notification.hourOrDay, notification.minuteOrMonth, notificationTitle.text.toString(), notificationText.text.toString())
            finish()
        }
    }
}