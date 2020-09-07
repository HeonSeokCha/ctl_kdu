package com.chs.ctl_kdu.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.adapter.dto.ClassRoom
import com.chs.ctl_kdu.databinding.ItemClassroomBinding

class classRoomAdapter(private val item:List<ClassRoom>):RecyclerView.Adapter<classRoomAdapter.classRoomViewHolder>() {
    class classRoomViewHolder(val binding:ItemClassroomBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): classRoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_classroom,parent,false)
        val viewHolder = classRoomViewHolder(ItemClassroomBinding.bind(view))
        return viewHolder
    }

    override fun onBindViewHolder(holder: classRoomViewHolder, position: Int) {
        holder.binding.data = item[position]
    }

    override fun getItemCount(): Int = item.size
}