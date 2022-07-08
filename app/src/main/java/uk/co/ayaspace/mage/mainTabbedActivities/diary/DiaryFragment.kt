package uk.co.ayaspace.mage.mainTabbedActivities.diary

import uk.co.ayaspace.mage.utils.recyclyerUtils.DiaryRecyclerAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.mage.databinding.FragmentDiaryBinding
import uk.co.ayaspace.mage.model.Entry
import uk.co.ayaspace.mage.utils.CustomPreferenceManager
import uk.co.ayaspace.mage.utils.PreferenceHelper
import java.util.*
import kotlin.collections.ArrayList

class DiaryFragment : Fragment() {

    private var _binding: FragmentDiaryBinding? = null
    lateinit var entryListView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appContext: Context = Objects.requireNonNull(this.activity)!!.applicationContext
        val preferenceHelper: CustomPreferenceManager by lazy { PreferenceHelper(appContext) }

        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val x: ArrayList<Entry> = ArrayList<Entry>()
        val entry1 = Entry("01-02-2022", "This is an entry", "I wrote some cool stuff!!")
        x.add(entry1)

        entryListView = binding.mainEntryList
        val layoutManager = LinearLayoutManager(appContext)
        entryListView.layoutManager = layoutManager
        entryListView.adapter = DiaryRecyclerAdapter({ position -> onItemClicked(position) }, x)

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