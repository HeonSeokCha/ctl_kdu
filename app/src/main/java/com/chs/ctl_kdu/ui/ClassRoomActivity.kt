package com.chs.ctl_kdu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.viewmodel.ClassRoomViewModel

class ClassRoomActivity : AppCompatActivity() {
    private lateinit var viewmodel:ClassRoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_room)
    }
}