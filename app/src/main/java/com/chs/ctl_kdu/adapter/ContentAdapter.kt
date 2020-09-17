package com.chs.ctl_kdu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.databinding.ItemContentBinding
import com.chs.ctl_kdu.dto.Content

class ContentAdapter(
    private val item: List<Content>,
    private val clicklistener: ()->Unit
):RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {
    class ContentViewHolder(val binding: ItemContentBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_content,parent,false)
        val viewHolder = ContentViewHolder(ItemContentBinding.bind(view))
        view.setOnClickListener {
            clicklistener.invoke()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.binding.data = item[position]
    }

    override fun getItemCount() = item.size
}