package uk.co.ayaspace.mage

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uk.co.ayaspace.mage.databinding.ActivityMainBinding
import uk.co.ayaspace.mage.firstTimeUserActivities.FirstTimeUserWelcomeScreen
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val preferenceHelper: CustomPreferenceManager by lazy { PreferenceHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Temporarily force redirect to First Time User Activities
        if (!preferenceHelper.getUserExists()) {
            val intent = Intent(this, FirstTimeUserWelcomeScreen::class.java)
            startActivity(intent);
            finish()
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_diary, R.id.navigation_alarms, R.id.navigation_settings
            )
        )
        navView.setupWithNavController(navController)
    }
}