package uk.co.ayaspace.mage.firstTimeUserActivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.utils.AwsDataAccess
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.PreferenceHelper
import uk.co.ayaspace.mage.utils.StringHasher
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

class FirstTimeUserSignUpActivity : AppCompatActivity() {
    private val preferenceHelper: CustomPreferenceManager by lazy {
        PreferenceHelper(
            applicationContext
        )
    }

    lateinit var usernameEntry: EditText
    lateinit var signUpButton: Button
    lateinit var emailEntry: EditText
    lateinit var passwordEntry: EditText
    lateinit var passwordConfirm: EditText

    fun md5(str: String): ByteArray =
        MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_user_sign_up)

        usernameEntry = findViewById(R.id.username_entry)
        signUpButton = findViewById(R.id.sign_up_button)
        emailEntry = findViewById(R.id.sign_up_email_entry)
        passwordEntry = findViewById(R.id.sign_up_password_entry)
        passwordConfirm = findViewById(R.id.sign_up_confirm_password_entry)

        signUpButton.setOnClickListener {
            var password = passwordEntry.text.toString()
            val passwordConfirmation = passwordConfirm.text.toString()
            val email = emailEntry.text.toString()
            val username = usernameEntry.text.toString()
            if (password.equals(passwordConfirmation) && password.isNotBlank()) {
                password = StringHasher().generateHash(password)

                var dbaccess = AwsDataAccess(this)
                dbaccess.insertNewUser(
                    email,
                    username,
                    password
                )

                intent = Intent(this, FirstTimeUserConfirmation::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}