package uk.co.ayaspace.mage.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import uk.co.ayaspace.mage.MainActivity

class AlarmBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1!!.action.equals("uk.co.ayaspace.alarm")) {
            val extras = p1.extras
            Toast.makeText(p0, extras?.getString("payload"), Toast.LENGTH_LONG).show()

        }
        else if (p1!!.action.equals("android.intent.action.BOOT_COMPLETED")) {

        }
    }

}