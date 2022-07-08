package uk.co.ayaspace.mage.utils.recyclyerUtils

import android.content.Intent
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.model.Entry

class DiaryRecyclerViewHolder(private val onItemClicked: (position: Int) -> Unit, view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
    var entryNameTextView: TextView = view.findViewById(R.id.entryTitle)
    var entryDate: TextView = view.findViewById(R.id.dateView)
    lateinit var entry: Entry
    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val position = adapterPosition
        onItemClicked(position)
    }
}