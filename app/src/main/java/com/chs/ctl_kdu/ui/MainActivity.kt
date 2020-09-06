package com.chs.ctl_kdu.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements


class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel:MainViewModel
    private lateinit var doc:Elements
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
            .create(MainViewModel::class.java)

        initClick()
    }

    private fun initClick(){
        btn_OK.setOnClickListener {
            viewmodel.getList().observe(this, Observer {response->
                Log.d("Response",response)
                test.text = Jsoup.parse(response).text().toString()
            })
        }
    }
}