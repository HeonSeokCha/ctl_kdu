package com.chs.ctl_kdu.ui

import User
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.retrofitServices.retrofitApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class MainActivity : AppCompatActivity() {
    private val jsessionId:String by lazy { intent.getStringExtra("jsessionId")!! }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ctl.kduniv.ac.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val service = retrofit.create(retrofitApi::class.java)

        btn_OK.setOnClickListener {
            CoroutineScope(IO).launch {
                val response = Jsoup.connect("https://ctl.kduniv.ac.kr//lms/myLecture/doListView.dunet?mnid=201008840728")
                    .header("Cookie",jsessionId)
                    .get()
                val contentData:Elements = response.select("div.lecture_pos_box ul li")
                Log.d("HTML",contentData.toString())
            }
        }
    }
}