package uk.co.ayaspace.mage.model

import org.w3c.dom.Text
import java.lang.Integer.parseInt
import java.util.*

class Entry (
    var date: String,
    var title: String,
    var content: String
        ) {
    lateinit var dateText: String
    lateinit var dateObject: Date
    lateinit var entryID: Number

    init {
        createDateString()
    }

    private fun createDateString() {
        val day: Int = parseInt(date.split("-")[0])
        val month: Int = parseInt(date.split("-")[1])
        val year: Int = parseInt(date.split("-")[2])
        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = year
        cal[Calendar.MONTH] = month - 1
        cal[Calendar.DAY_OF_MONTH] = day
        dateObject = cal.time
        val dayString: String = dateObject.toString().split(" ")[0]
        val dateString: String = dateObject.toString().split(" ")[1]
        val monthString: String = dateObject.toString().split(" ")[2]
        val yearString: String = dateObject.toString().split(" ")[5]
        dateText = "$dayString $monthString $dateString $yearString"
    }
}