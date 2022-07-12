package uk.co.ayaspace.mage.model

class Alarm (
    var label: String,
    var hourOrDay: Int,
    var minuteOrMonth: Int,
    var daily: Boolean,
    var isNotification: Boolean,
    var alarmID: Int,
    var descriptionText: String = ""
        ) {

}