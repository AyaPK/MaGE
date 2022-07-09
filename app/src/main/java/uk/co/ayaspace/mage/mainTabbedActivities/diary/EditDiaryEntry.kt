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

class EditDiaryEntry : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var entryTitleView: TextView
    lateinit var entryContentView: TextView
    lateinit var cancelButton: Button
    lateinit var saveButton: Button
    lateinit var dateButton: ImageButton
    lateinit var dateText: TextView
    lateinit var dataAccess: DataAccess

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_diary_entry)
        dataAccess = DataAccess(this)

        val entryTitle = intent.getStringExtra("title")
        val entryContent = intent.getStringExtra("content")
        val entryDate = intent.getStringExtra("date")
        val id = intent.getIntExtra("id", -1)

        entryTitleView = findViewById(R.id.edittext_entry_title)
        entryContentView = findViewById(R.id.edit_text_content)
        cancelButton = findViewById(R.id.edit_cancel_button)
        dateButton = findViewById(R.id.edit_date_button)
        saveButton = findViewById(R.id.edit_save_button)
        dateText = findViewById(R.id.edit_date_text)

        entryTitleView.text = entryTitle
        entryContentView.text = entryContent
        dateText.text = fixDateFormat(entryDate)

        cancelButton.setOnClickListener {
            finish()
        }

        dateButton.setOnClickListener {
            val mDatePickerDialogFragment: CustomDatePicker = CustomDatePicker()
            mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
        }
        dateText.setOnClickListener {
            val mDatePickerDialogFragment: CustomDatePicker = CustomDatePicker()
            mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
        }

        saveButton.setOnClickListener {
            val entry: Entry = Entry(dateText.text.toString(), entryTitleView.text.toString(), entryContentView.text.toString())
            entry.entryID = id
            dataAccess.updateEntry(entry)
            intent.putExtra("newTitle", entry.title)
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

    private fun fixDateFormat(oldDate: String?): String {
        val months: ArrayList<String> = ArrayList<String>()
        months.add("Jan")
        months.add("Feb")
        months.add("Mar")
        months.add("Apr")
        months.add("May")
        months.add("Jun")
        months.add("Jul")
        months.add("Aug")
        months.add("Sep")
        months.add("Oct")
        months.add("Nov")
        months.add("Dec")

        val day = oldDate?.split(" ")?.get(1)
        val monthText = oldDate?.split(" ")?.get(2)
        val year = oldDate?.split(" ")?.get(3)

        return "$day-${months.indexOf(monthText)}-$year"
    }

}