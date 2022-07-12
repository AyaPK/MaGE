package uk.co.ayaspace.mage.firstTimeUserActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uk.co.ayaspace.mage.R
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.view.View
import android.widget.*
import uk.co.ayaspace.mage.utils.CustomDatePicker
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.InformationDialog
import uk.co.ayaspace.mage.utils.PreferenceHelper


class FirstTimeUserDetailsScreen : AppCompatActivity(), OnDateSetListener {
    private val preferenceHelper: CustomPreferenceManager by lazy { PreferenceHelper(applicationContext) }

    lateinit var birthdayDateText: TextView
    lateinit var birthdayPickButton: ImageButton
    lateinit var pronounsSpinner1: Spinner
    lateinit var pronounsSpinner2: Spinner
    lateinit var informationText: TextView
    lateinit var selectedPronoun1: String
    lateinit var selectedPronoun2: String
    lateinit var nameEntry: TextView
    lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_user_details_screen)
        pronounsSpinner1 = findViewById(R.id.pronounsSpinner1)
        pronounsSpinner2 = findViewById(R.id.pronounsSpinner2)
        informationText = findViewById(R.id.what_is_this_used_for)
        nextButton = findViewById(R.id.nextButton)
        nameEntry = findViewById(R.id.nameEntry)
        setUpSpinner1(pronounsSpinner1, this, resources.getStringArray(R.array.pronouns1))
        setUpSpinner2(pronounsSpinner2, this, resources.getStringArray(R.array.pronouns2))

        birthdayDateText = findViewById(uk.co.ayaspace.mage.R.id.birthday_text)
        birthdayPickButton = findViewById(R.id.birthday_button)
        birthdayPickButton.setOnClickListener {
            val mDatePickerDialogFragment: CustomDatePicker = CustomDatePicker()
            mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
        }
        birthdayDateText.setOnClickListener {
            val mDatePickerDialogFragment: CustomDatePicker = CustomDatePicker()
            mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
        }

        informationText.setOnClickListener {
            val informationDialog = InformationDialog(resources.getString(R.string.what_the_info_is_used_for))
            informationDialog.show(supportFragmentManager, "")
        }

        nextButton.setOnClickListener {
            preferenceHelper.setUserName(nameEntry.text.toString())
            preferenceHelper.setBirthday(birthdayDateText.text.toString())
            preferenceHelper.setPronouns1(selectedPronoun1)
            preferenceHelper.setPronouns2(selectedPronoun2)

            val intent = Intent(this, FirstTimeUserBasicSettings::class.java)
            startActivity(intent)
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
        birthdayDateText.text = dateTime
    }

    private fun setUpSpinner1(spinner: Spinner, p0: Context, content: Array<String>) {
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, content)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                selectedPronoun1 = content[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setUpSpinner2(spinner: Spinner, p0: Context, content: Array<String>) {
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, content)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                selectedPronoun2 = content[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

}
