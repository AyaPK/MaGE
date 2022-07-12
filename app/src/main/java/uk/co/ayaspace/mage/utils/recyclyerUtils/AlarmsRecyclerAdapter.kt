package uk.co.ayaspace.mage.utils.recyclyerUtils

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.mage.R
import uk.co.ayaspace.mage.model.Alarm

class AlarmsRecyclerAdapter(private val onItemClicked: (position: Int) -> Unit, private var itemsList: List<Alarm>) : RecyclerView.Adapter<AlarmsRecyclerViewHolder>() {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmsRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.alarms_cardview, parent, false)
        return AlarmsRecyclerViewHolder(onItemClicked, itemView)
    }
    override fun onBindViewHolder(holder: AlarmsRecyclerViewHolder, position: Int) {
        val res: Resources = holder.itemView.context.resources
        val item = itemsList[position]
        holder.alarmNameTextView.text = item.label.toString()
        if(item.isNotification) {
            holder.theImage.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_notification_important_24, null))
        } else {
            holder.theImage.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.ic_baseline_alarm_24, null))
        }
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}