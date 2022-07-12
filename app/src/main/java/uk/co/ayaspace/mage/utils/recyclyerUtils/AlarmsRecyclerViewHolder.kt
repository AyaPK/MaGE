package uk.co.ayaspace.mage.utils.recyclyerUtils

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.model.Entry

class AlarmsRecyclerViewHolder(private val onItemClicked: (position: Int) -> Unit, view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
    var alarmNameTextView: TextView = view.findViewById(R.id.alarm_name_recycler_label)
    var theImage: ImageView = view.findViewById(R.id.alarm_icon)
    lateinit var entry: Entry
    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val position = adapterPosition
        onItemClicked(position)
    }
}