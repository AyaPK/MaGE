package uk.co.ayaspace.mage.firstTimeUserActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import uk.co.ayaspace.mage.MainActivity
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.PreferenceHelper

class FirstTimeUserConfirmation : AppCompatActivity() {
    private val preferenceHelper: CustomPreferenceManager by lazy { PreferenceHelper(applicationContext) }

    lateinit var nextButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_user_confirmation)
        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            preferenceHelper.setUserExists(true)

            startActivity(intent)
            finish()
        }
    }
}