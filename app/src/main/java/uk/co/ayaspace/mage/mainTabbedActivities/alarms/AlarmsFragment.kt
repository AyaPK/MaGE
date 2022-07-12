package uk.co.ayaspace.mage.mainTabbedActivities.alarms

import android.app.Activity
import uk.co.ayaspace.mage.utils.recyclyerUtils.AlarmsRecyclerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.databinding.FragmentAlarmsBinding
import uk.co.ayaspace.mage.model.Alarm
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.DataAccess
import uk.co.ayaspace.mage.utils.PreferenceHelper
import uk.co.ayaspace.mage.utils.recyclyerUtils.DiaryRecyclerAdapter
import java.util.*

class AlarmsFragment : Fragment() {

    private var _binding: FragmentAlarmsBinding? = null
    lateinit var alarmListView: RecyclerView
    lateinit var addButton: FloatingActionButton
    lateinit var alarmAddButton: FloatingActionButton
    lateinit var notificationAddButton: FloatingActionButton
    lateinit var blackoutOverlay: RelativeLayout
    lateinit var newNotificationText: TextView
    lateinit var newAlarmText: TextView
    lateinit var dataAccess: DataAccess
    lateinit var alarmList: ArrayList<Alarm>
    lateinit var alarmAdapter: AlarmsRecyclerAdapter
    lateinit var appContext: Context

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        appContext = this.requireActivity().applicationContext
        val preferenceHelper: CustomPreferenceManager by lazy { PreferenceHelper(appContext) }
        dataAccess = DataAccess(requireContext())

        _binding = FragmentAlarmsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addButton = binding.floatingButtonAdd
        alarmAddButton = binding.floatingButtonNewAlarm
        notificationAddButton = binding.floatingButtonNotifications
        blackoutOverlay = binding.transparentOverlay
        newAlarmText = binding.newAlarmText
        newNotificationText = binding.newNoficationText

        alarmList = dataAccess.getAllAlarms()

        alarmListView = binding.mainEntryList
        val layoutManager = LinearLayoutManager(appContext)
        alarmListView.layoutManager = layoutManager
        alarmAdapter = AlarmsRecyclerAdapter({ position -> onItemClicked(position) }, alarmList)
        alarmListView.adapter = alarmAdapter

        addButton.setOnClickListener {
            showSubIcons()
        }

        alarmAddButton.setOnClickListener {
            val intent: Intent = Intent(appContext, NewAlarm::class.java)
            hideSubIcons()
            getResultFromAlarmClicked.launch(intent)
        }

        notificationAddButton.setOnClickListener {
            val intent: Intent = Intent(appContext, NewNotification::class.java)
            hideSubIcons()
            getResultFromAlarmClicked.launch(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClicked(position: Int) {
        val alarm : Alarm = alarmList[position]
        if(!alarm.isNotification) {
            val intent: Intent = Intent(appContext, EditAlarm::class.java)
            intent.putExtra("label", alarm.label)
            intent.putExtra("hour", alarm.hourOrDay)
            intent.putExtra("min", alarm.minuteOrMonth)
            intent.putExtra("daily", alarm.daily)
            intent.putExtra("id", alarm.alarmID)
            getResultFromAlarmClicked.launch(intent)
        } else {
            //pass
        }
    }

    private fun showSubIcons() {
        val appContext: Context = this.requireActivity().applicationContext
        addButton.setImageDrawable(ContextCompat.getDrawable(appContext, R.drawable.ic_baseline_clear_24))
        blackoutOverlay.visibility = View.VISIBLE
        alarmAddButton.visibility = View.VISIBLE
        notificationAddButton.visibility = View.VISIBLE
        newNotificationText.visibility = View.VISIBLE
        newAlarmText.visibility = View.VISIBLE

        addButton.setOnClickListener { hideSubIcons() }
    }

    private fun hideSubIcons() {
        val appContext: Context = this.requireActivity().applicationContext
        addButton.setImageDrawable(ContextCompat.getDrawable(appContext, R.drawable.ic_baseline_add_24))
        blackoutOverlay.visibility = View.GONE
        alarmAddButton.visibility = View.GONE
        notificationAddButton.visibility = View.GONE
        newNotificationText.visibility = View.GONE
        newAlarmText.visibility = View.GONE

        addButton.setOnClickListener { showSubIcons() }
    }

    private var getResultFromAlarmClicked = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            alarmList = dataAccess.getAllAlarms()
            alarmAdapter = AlarmsRecyclerAdapter({ position -> onItemClicked(position) }, alarmList)
            alarmAdapter.notifyDataSetChanged()
            alarmListView.adapter = alarmAdapter
        }
    }
}