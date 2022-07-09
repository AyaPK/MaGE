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


class CustomDatePicker() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mCalendar: Calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val dateTime = simpleDateFormat.format(mCalendar.time).toString()
        val dayOfMonth = dateTime.split("-")[0].toInt()
        val month = dateTime.split("-")[1].toInt()-1
        val year = dateTime.split("-")[2].toInt()

        val dp = DatePickerDialog(requireActivity(), activity as OnDateSetListener?, year, month, dayOfMonth)
        return dp
    }
}