package uk.co.ayaspace.mage.mainTabbedActivities.alarms

import uk.co.ayaspace.mage.utils.recyclyerUtils.AlarmsRecyclerAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.mage.databinding.FragmentAlarmsBinding
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.PreferenceHelper
import java.util.*

class AlarmsFragment : Fragment() {

    private var _binding: FragmentAlarmsBinding? = null
    lateinit var alarmListView: RecyclerView

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClicked(position: Int) {
        //pass
    }
}