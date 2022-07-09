package uk.co.ayaspace.mage.mainTabbedActivities.diary

import uk.co.ayaspace.mage.utils.recyclyerUtils.DiaryRecyclerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uk.co.ayaspace.mage.databinding.FragmentDiaryBinding
import uk.co.ayaspace.mage.model.Entry
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.DataAccess
import uk.co.ayaspace.mage.utils.PreferenceHelper
import java.util.*
import kotlin.collections.ArrayList

class DiaryFragment : Fragment() {

    private var _binding: FragmentDiaryBinding? = null
    lateinit var entryListView: RecyclerView
    lateinit var dataAccess: DataAccess
    lateinit var floatingButton: FloatingActionButton
    lateinit var entryList: ArrayList<Entry>

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
        dataAccess = DataAccess(appContext)

        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        entryList = dataAccess.getAllDiaryEntries()

        entryListView = binding.mainEntryList
        val layoutManager = LinearLayoutManager(appContext)
        entryListView.layoutManager = layoutManager
        entryListView.adapter = DiaryRecyclerAdapter({ position -> onItemClicked(position) }, entryList)

        floatingButton = binding.floatingButtonAddDiaryEntry
        floatingButton.setOnClickListener {
            val intent = Intent(appContext, NewDiaryEntry::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClicked(position: Int) {
        val entry: Entry = entryList[position]
        val intent: Intent = Intent(this.requireActivity().applicationContext, ViewDiaryEntry::class.java)
        intent.putExtra("title", entry.title)
        intent.putExtra("content", entry.content)
        intent.putExtra("date", entry.dateText)
        startActivity(intent)
    }
}