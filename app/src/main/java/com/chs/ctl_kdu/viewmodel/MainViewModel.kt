package com.chs.ctl_kdu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chs.ctl_kdu.adapter.dto.ClassRoom
import com.chs.ctl_kdu.retrofitServices.CtlApi
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    val api = CtlApi.create()

    fun getClassRoom():LiveData<List<ClassRoom>>{
        val ret = MutableLiveData<List<ClassRoom>>()

        api.doListView("201008840728").enqueue(object: Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    val doc = Jsoup.parse(response.body()!!.string())
                    with(doc.select("li.box_li")) {
                        for(i in this.indices){
                            ret.value = listOf(ClassRoom(
                                url = this[i].select("div.accordion a").attr("href").split("'")[1],
                                title = this[i].select("div.accordion a strong").text(),
                                professor = this[i].select("div.accordion a span.term").text(),
                                credit = this[i].select("div.accordion a span.place").text(),
                                lec_type = this[i].select("div.accordion span.lec_type").text()))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                ret.value = listOf(ClassRoom())
            }
        })
        return ret
    }
}