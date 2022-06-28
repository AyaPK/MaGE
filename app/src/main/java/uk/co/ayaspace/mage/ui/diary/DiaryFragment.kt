package uk.co.ayaspace.mage.ui.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uk.co.ayaspace.mage.databinding.FragmentDiaryBinding

class DiaryFragment : Fragment() {

    private lateinit var diaryViewModel: DiaryViewModel
    private var _binding: FragmentDiaryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        diaryViewModel =
            ViewModelProvider(this).get(DiaryViewModel::class.java)

        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDiary
        diaryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}