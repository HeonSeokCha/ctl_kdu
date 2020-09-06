package com.chs.ctl_kdu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chs.ctl_kdu.retrofitServices.CtlApi
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    val api = CtlApi.create()

    fun getList():LiveData<String>{
        val ret = MutableLiveData<String>()
        api.doListView("201008840728").enqueue(object: Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>) {
                ret.value = response.body()!!.string()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                ret.value = ""
            }
        })
        return ret
    }
}