package uk.co.ayaspace.mage.mainTabbedActivities.alarms

import uk.co.ayaspace.mage.utils.recyclyerUtils.AlarmsRecyclerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.databinding.FragmentAlarmsBinding
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.PreferenceHelper
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

        _binding = FragmentAlarmsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addButton = binding.floatingButtonAdd
        alarmAddButton = binding.floatingButtonNewAlarm
        notificationAddButton = binding.floatingButtonNotifications
        blackoutOverlay = binding.transparentOverlay
        newAlarmText = binding.newAlarmText
        newNotificationText = binding.newNoficationText

        val x: ArrayList<String> = ArrayList<String>()
        x.add("aaaa")
        x.add("aaaa")
        x.add("aaaa")
        x.add("aaaa")
        x.add("aaaa")
        x.add("aaaa")
        x.add("aaaa")
        x.add("aaaa")

        alarmListView = binding.mainEntryList
        val layoutManager = LinearLayoutManager(appContext)
        alarmListView.layoutManager = layoutManager
        alarmListView.adapter = AlarmsRecyclerAdapter({ position -> onItemClicked(position) }, x)

        addButton.setOnClickListener {
            showSubIcons()
        }

        alarmAddButton.setOnClickListener {
            val intent: Intent = Intent(appContext, NewAlarm::class.java)
            hideSubIcons()
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClicked(position: Int) {
        //pass
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
}