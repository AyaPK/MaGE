package uk.co.ayaspace.mage.utils.recyclyerUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.mage.R

internal class AlarmsRecyclerAdapter(private val onItemClicked: (position: Int) -> Unit, private var itemsList: List<String>) : RecyclerView.Adapter<AlarmsRecyclerViewHolder>() {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmsRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.alarms_cardview, parent, false)
        return AlarmsRecyclerViewHolder(onItemClicked, itemView)
    }
    override fun onBindViewHolder(holder: AlarmsRecyclerViewHolder, position: Int) {
        val item = itemsList[position]
//        holder.book = item
//        holder.bookNameTextView.text = item.bookName
//        holder.bookRatingBar.rating = item.rating.toFloat()
//        val day = item.date.toString().split(" ")[0]
//        val date = item.date.toString().split(" ")[1]
//        val month = item.date.toString().split(" ")[2]
//        val year = item.date.toString().split(" ")[5]
//        holder.bookDate.text = item.dateText
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}