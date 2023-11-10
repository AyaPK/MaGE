package uk.co.ayaspace.mage.mainTabbedActivities.settings

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import uk.co.ayaspace.mage.databinding.FragmentSettingsBinding
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.PreferenceHelper

class SettingsFragment : Fragment() {

    lateinit var nameEntry: TextView
    lateinit var pronounsSpinner1: Spinner
    lateinit var pronounsSpinner2: Spinner
    lateinit var birthdayText: TextView
    lateinit var birthdayButton: ImageButton
    lateinit var settingsTimeFormat: Spinner
    lateinit var settingsDateFormat: Spinner
    lateinit var settingsCloudToggle: SwitchMaterial
    lateinit var selectedPronoun1: String
    lateinit var selectedPronoun2: String
    lateinit var settingsCancel: Button
    lateinit var settingsSave: Button

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appContext: Context = this.requireActivity().applicationContext
        val preferenceHelper: CustomPreferenceManager by lazy { PreferenceHelper(appContext) }
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        nameEntry = binding.settingsNameEntry
        pronounsSpinner1 = binding.settingsPronouns1
        pronounsSpinner2 = binding.settingsPronouns2
        birthdayText = binding.settingsBirthdayText
        birthdayButton = binding.settingsBirthdayButton
        settingsTimeFormat = binding.settingsTimeFormat
        settingsDateFormat = binding.settingsDateFormat
        settingsCloudToggle = binding.settingsToggle
        settingsSave = binding.settingsSaveButton

        nameEntry.text = preferenceHelper.getUserName()
        birthdayText.text = preferenceHelper.getBirthday()
        setUpSpinner1(pronounsSpinner1 ,appContext, resources.getStringArray(uk.co.ayaspace.mage.R.array.pronouns1), preferenceHelper.getPronouns1())
        setUpSpinner2(pronounsSpinner2 ,appContext, resources.getStringArray(uk.co.ayaspace.mage.R.array.pronouns2), preferenceHelper.getPronouns2())

        settingsSave.setOnClickListener {
            preferenceHelper.setUserName(nameEntry.text.toString())
            preferenceHelper.setBirthday(birthdayText.text.toString())
            preferenceHelper.setPronouns2(selectedPronoun2)
            preferenceHelper.setPronouns1(selectedPronoun1)

            Toast.makeText(appContext,
                "Saved", Toast.LENGTH_SHORT)
                .show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun fixDateFormat(oldDate: String?): String {
        val months: ArrayList<String> = ArrayList<String>()
        months.add("Jan")
        months.add("Feb")
        months.add("Mar")
        months.add("Apr")
        months.add("May")
        months.add("Jun")
        months.add("Jul")
        months.add("Aug")
        months.add("Sep")
        months.add("Oct")
        months.add("Nov")
        months.add("Dec")

        val day = oldDate?.split(" ")?.get(0)
        val monthText = oldDate?.split(" ")?.get(1)
        val year = "2022"

        return "$day-${months.indexOf(monthText)+1}-$year"
    }

    private fun setUpSpinner1(spinner: Spinner, p0: Context, content: Array<String>, selectedPronoun: String) {
        val adapter = ArrayAdapter(p0, R.layout.simple_spinner_item, content)
        spinner.adapter = adapter

        var positionOfSelectedPronoun = 0
        for (pronoun in content) {
            if (pronoun == selectedPronoun) {
                spinner.setSelection(positionOfSelectedPronoun)
                break
            } else {
                positionOfSelectedPronoun++
            }
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                selectedPronoun1 = content[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setUpSpinner2(spinner: Spinner, p0: Context, content: Array<String>, selectedPronoun: String) {
        val adapter = ArrayAdapter(p0, android.R.layout.simple_spinner_item, content)
        spinner.adapter = adapter

        var positionOfSelectedPronoun = 0
        for (pronoun in content) {
            if (pronoun == selectedPronoun) {
                spinner.setSelection(positionOfSelectedPronoun)
                break
            } else {
                positionOfSelectedPronoun++
            }
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                selectedPronoun2 = content[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}