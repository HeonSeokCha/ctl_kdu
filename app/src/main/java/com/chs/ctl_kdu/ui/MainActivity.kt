package com.chs.ctl_kdu.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.retrofitServices.CtlApi
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = CtlApi.create()

        btn_OK.setOnClickListener {
            api.doListView("201008840728").enqueue(object: Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val parse = Jsoup.parse(response.toString())
                    Log.d("Success", parse.toString())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("Failed",t.localizedMessage)
                }
            })
        }
    }
}