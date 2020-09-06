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


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ctl.kduniv.ac.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(retrofitApi::class.java)
        var sessionId:String = "기본값"

        service.doLogin("1524037", "12345qwer!", "UN").enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                sessionId = response.headers()["Set-Cookie"]!!.split(";")[0]
                Log.d("Success", response.headers()["Set-Cookie"]!!.split(";")[0])
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Failed", t.localizedMessage)
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        btn_OK.setOnClickListener {
            CoroutineScope(IO).launch {
                val response = Jsoup.connect("https://ctl.kduniv.ac.kr//lms/myLecture/doListView.dunet?mnid=201008840728")
                    .header("Cookie",sessionId)
                    .get()
                val contentData:Elements = response.select("div.lecture_pos_box ul li")
                Log.d("HTML",contentData.toString())
            }
        }
    }
}