package uk.co.ayaspace.mage.mainTabbedActivities.diary

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.model.Entry
import uk.co.ayaspace.mage.utils.CustomDatePicker
import uk.co.ayaspace.mage.utils.DataAccess

class NewDiaryEntry : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var cancelButton: Button
    lateinit var saveButton: Button
    lateinit var entryTitle: TextView
    lateinit var entryContent: TextView
    lateinit var dateText: TextView
    lateinit var dateButton: ImageButton
    lateinit var dataAccess: DataAccess

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_diary_entry)
        dataAccess = DataAccess(this)

        dateText = findViewById(R.id.date_text)
        dateButton = findViewById(R.id.date_button)
        entryTitle = findViewById(R.id.edittext_entry_title)
        entryContent = findViewById(R.id.diary_text_content)

        dateButton.setOnClickListener {
            val mDatePickerDialogFragment: CustomDatePicker = CustomDatePicker()
            mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
        }
        dateText.setOnClickListener {
            val mDatePickerDialogFragment: CustomDatePicker = CustomDatePicker()
            mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
        }

        cancelButton = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }

        saveButton = findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            val entry: Entry = Entry(dateText.text.toString(), entryTitle.text.toString(), entryContent.text.toString())
            dataAccess.insertDiaryEntry(entry)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onDateSet(p0: android.widget.DatePicker?, p1: Int, p2: Int, p3: Int) {
        val mCalendar: Calendar = Calendar.getInstance()
        mCalendar.set(Calendar.YEAR, p1)
        mCalendar.set(Calendar.MONTH, p2)
        mCalendar.set(Calendar.DAY_OF_MONTH, p3)

        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val dateTime = simpleDateFormat.format(mCalendar.time).toString()
        dateText.text = dateTime
    }
}