package uk.co.ayaspace.mage.mainTabbedActivities.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the settings tab"
    }
    val text: LiveData<String> = _text
}