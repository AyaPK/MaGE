package uk.co.ayaspace.mage.utils

import android.content.Context
import android.util.Log
import uk.co.ayaspace.mage.BuildConfig
import java.sql.DriverManager
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class AwsDataAccess(private val context: Context) {
    val DATABASE_NAME = "mage_dev_mysql"
    val url = "jdbc:mysql://mage-dev-db.ct4ijvipbtpt.eu-west-2.rds.amazonaws.com:3306/" +
            DATABASE_NAME + "?useSSL=false"
    val DB_USERNAME = BuildConfig.AWS_DB_USER
    val DB_PASSWORD = BuildConfig.AWS_DB_PASS

    fun insertNewUser(email: String, username: String, password: String) {
        Thread {
            try {
                Class.forName("com.mysql.jdbc.Driver")
                val connection: Connection =
                    DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)
                val statement: Statement = connection.createStatement()
                // add to RDS DB:
                statement.execute("INSERT INTO users(email, username, password_hash)" +
                        "VALUES('$email','$username', '$password')")
                connection.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}