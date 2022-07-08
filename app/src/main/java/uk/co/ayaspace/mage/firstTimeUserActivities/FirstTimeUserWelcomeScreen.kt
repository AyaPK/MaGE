package uk.co.ayaspace.mage.firstTimeUserActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.utils.InformationDialog

class FirstTimeUserWelcomeScreen : AppCompatActivity() {
    lateinit var getStartedButton: Button
    lateinit var whatIsMage: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_user_welcome_screen)
        getStartedButton = findViewById(R.id.getStartedButton)
        whatIsMage = findViewById(R.id.what_is_mage)

        getStartedButton.setOnClickListener {
            val intent = Intent(this, FirstTimeUserDetailsScreen::class.java)
            startActivity(intent)
            finish()
        }

        whatIsMage.setOnClickListener {
            val informationDialog = InformationDialog(resources.getString(R.string.what_mage_is))
            informationDialog.show(supportFragmentManager, "")
        }
    }
}