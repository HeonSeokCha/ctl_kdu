package com.chs.ctl_kdu.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.adapter.dto.ClassRoom
import com.chs.ctl_kdu.databinding.ItemClassroomBinding

class ClassRoomAdapter(private val item:List<ClassRoom>,
                       private val clickListener:(course_id:String,class_no:String)-> Unit):RecyclerView.Adapter<ClassRoomAdapter.ClassRoomViewHolder>() {
    class ClassRoomViewHolder(val binding:ItemClassroomBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassRoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_classroom,parent,false)
        val viewHolder = ClassRoomViewHolder(ItemClassroomBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(
                item[viewHolder.adapterPosition].course_id,
                item[viewHolder.adapterPosition].class_no)
}

return viewHolder
}

override fun onBindViewHolder(holder: ClassRoomViewHolder, position: Int) {
    holder.binding.data = item[position]
}

override fun getItemCount(): Int = item.size
}