package com.chs.ctl_kdu.ui

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.adapter.ClassRoomAdapter
import com.chs.ctl_kdu.adapter.dto.ClassRoom
import com.chs.ctl_kdu.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel:MainViewModel
    private lateinit var ClassRoomAdapter: ClassRoomAdapter
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

    private fun initRecyclerView(){
        classList = mutableListOf()
        ClassRoomAdapter = ClassRoomAdapter(classList){course_id,class_no->
            val intent = Intent(this,ClassRoomActivity::class.java)
            intent.putExtra("course_id",course_id)
            intent.putExtra("class_no",class_no)
            startActivity(intent)
        }
        Rv_classRoom.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = ClassRoomAdapter
            this.setHasFixedSize(true)
        }
        getList()
    }

    private fun getList(){
        viewmodel.getClassRoom().observe(this, Observer { list->
            if(list.isNotEmpty()){
                classList.addAll(list)
                ClassRoomAdapter.notifyDataSetChanged()
            }
        })
    }
}
