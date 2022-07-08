package uk.co.ayaspace.mage.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import uk.co.ayaspace.mage.R

class InformationDialog(var textToShow: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(textToShow)
                .setNegativeButton(R.string.okay,
                    DialogInterface.OnClickListener { dialog, id -> })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}