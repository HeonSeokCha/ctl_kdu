package com.chs.ctl_kdu.ui

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.adapter.ContentAdapter
import com.chs.ctl_kdu.dto.Content
import com.chs.ctl_kdu.viewmodel.ClassRoomViewModel
import kotlinx.android.synthetic.main.activity_class_room.*

class ClassRoomActivity : AppCompatActivity() {
    private lateinit var viewmodel: ClassRoomViewModel
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var contentList: MutableList<Content>
    private val course_id: String by lazy { intent.getStringExtra("course_id").toString() }
    private val class_no: String by lazy { intent.getStringExtra("class_no").toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_room)
        viewmodel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(Application())
            .create(ClassRoomViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        contentList = mutableListOf()
        contentAdapter = ContentAdapter(contentList) { }
        Rv_content.apply {
            this.layoutManager = LinearLayoutManager(this@ClassRoomActivity)
            this.adapter = contentAdapter
            this.setHasFixedSize(true)
        }
        getList()
    }

    private fun getList() {
        viewmodel.getListView(course_id,class_no).observe(this, Observer { list ->
            if (list.isNotEmpty()) {
                contentList.addAll(list)
                contentAdapter.notifyDataSetChanged()

            }
            Log.d("ListSize",contentList.size.toString())
        })
    }
}