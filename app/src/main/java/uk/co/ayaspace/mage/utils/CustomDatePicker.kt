package uk.co.ayaspace.mage.utils

import android.R
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.res.Resources
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import java.text.ParseException
import java.util.*
import android.R.id


class CustomDatePicker : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = 2000
        val month = 0
        val dayOfMonth = 1
        val dp = DatePickerDialog(activity!!, activity as OnDateSetListener?, year, month, dayOfMonth)
        return dp
    }
}