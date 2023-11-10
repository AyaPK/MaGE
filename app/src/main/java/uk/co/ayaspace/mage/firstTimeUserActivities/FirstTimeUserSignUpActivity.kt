package uk.co.ayaspace.mage.firstTimeUserActivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.utils.AwsDataAccess
import uk.co.ayaspace.mage.utils.StringHasher

class FirstTimeUserSignUpActivity : AppCompatActivity() {
    lateinit var usernameEntry: EditText
    lateinit var signUpButton: Button
    lateinit var emailEntry: EditText
    lateinit var passwordEntry: EditText
    lateinit var passwordConfirm: EditText

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

                val dbaccess = AwsDataAccess(this)
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