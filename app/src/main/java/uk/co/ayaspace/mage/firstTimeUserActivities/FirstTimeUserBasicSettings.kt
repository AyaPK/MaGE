package uk.co.ayaspace.mage.firstTimeUserActivities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.utils.AwsDataAccess
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.InformationDialog
import uk.co.ayaspace.mage.utils.PreferenceHelper

class FirstTimeUserBasicSettings : AppCompatActivity() {
    private val preferenceHelper: CustomPreferenceManager by lazy {
        PreferenceHelper(
            applicationContext
        )
    }

    lateinit var selectedTimeFormat: String
    lateinit var selectedDateFormat: String
    lateinit var whatIsBackedUp: TextView
    lateinit var nameEntry: TextView
    lateinit var nextButton: Button
    lateinit var cloudToggle: SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_first_time_user_basic_settings)
        whatIsBackedUp = findViewById(R.id.what_is_backed_up)
        nextButton = findViewById(R.id.nextButton)
        cloudToggle = findViewById(R.id.data_backup_toggle)
        setUpSpinnerForTimeFormat(this)
        setUpSpinnerForDateFormat(this)

        whatIsBackedUp.setOnClickListener {
            val informationDialog =
                InformationDialog(resources.getString(R.string.what_is_backed_up_in_the_cloud))
            informationDialog.show(supportFragmentManager, "")
        }

        nextButton.setOnClickListener {

            var intent = Intent()
            if (cloudToggle.isChecked()) {
                intent = Intent(this, FirstTimeUserSignUpActivity::class.java)
            } else {
                intent = Intent(this, FirstTimeUserConfirmation::class.java)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun setUpSpinnerForTimeFormat(p0: Context) {
        val timeFormats = resources.getStringArray(R.array.time_formats)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, timeFormats
        )
        val spinner: Spinner = findViewById(R.id.time_format_spinner)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                selectedTimeFormat = timeFormats[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setUpSpinnerForDateFormat(p0: Context) {
        val dateFormats = resources.getStringArray(R.array.date_formats)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, dateFormats
        )
        val spinner: Spinner = findViewById(R.id.date_format_spinner)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                selectedTimeFormat = dateFormats[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}