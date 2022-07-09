package uk.co.ayaspace.mage.utils.recyclyerUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.model.Entry

class DiaryRecyclerAdapter(private val onItemClicked: (position: Int) -> Unit, private var itemsList: List<Entry>) : RecyclerView.Adapter<DiaryRecyclerViewHolder>() {
    lateinit var content: Entry

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.diary_cardview, parent, false)
        return DiaryRecyclerViewHolder(onItemClicked, itemView)
    }
    override fun onBindViewHolder(holder: DiaryRecyclerViewHolder, position: Int) {
        val item = itemsList[position]
        holder.entry = item
        holder.entryNameTextView.text = item.title
        holder.entryDate.text = item.dateText
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }

}