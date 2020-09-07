package com.chs.ctl_kdu.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.adapter.classRoomAdapter
import com.chs.ctl_kdu.adapter.dto.ClassRoom
import com.chs.ctl_kdu.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel:MainViewModel
    private lateinit var classRoomAdapter: classRoomAdapter
    private lateinit var classList:MutableList<ClassRoom>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
            .create(MainViewModel::class.java)
        initView()
        initRecyclerView()
    }


    private fun initView(){
        if(intent.hasExtra("userName")){
            userName.text = intent.getStringExtra("userName")
            userNo.text = intent.getStringExtra("userNo")
            userDeptName.text = intent.getStringExtra("deptNm")
        }
    }

    private fun getList(){
        viewmodel.getClassRoom().observe(this, Observer {list->
            if(list.isNotEmpty()){
                Log.d("List",list[0].title+"tnlkqkf")
                classList.addAll(list)
                classRoomAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun initRecyclerView(){
        classList = mutableListOf()
        classRoomAdapter = classRoomAdapter(classList)
        Rv_classRoom.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = classRoomAdapter
            this.setHasFixedSize(true)
        }
        getList()
    }
}