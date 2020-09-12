package com.chs.ctl_kdu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chs.ctl_kdu.adapter.dto.Content
import com.chs.ctl_kdu.retrofitServices.CtlApi
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassRoomViewModel:ViewModel() {
    private val api = CtlApi.create()

    fun getListView(course_id: String, class_no: String): LiveData<List<Content>>{
        val ret = MutableLiveData<List<Content>>()
        api.goClassRoom("201008254671", course_id, class_no,"3")
            .enqueue(object: Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        val doc = Jsoup.parse(response.body()!!.string())
                        with(doc.select(".module_act_detail")) {
                            for(i in this.indices) {
                                ret.value = listOf(
                                    Content(
                                        subject = this[i].select("a.subject").text().trim(),
                                        date = this[i].select("span.set_cont li:first-child").text().trim(),
                                        total_time = this[i].select("span.total_tile").text().trim(),
                                    )
                                )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    ret.value = listOf(Content())
                }
            })
        return ret
    }
}