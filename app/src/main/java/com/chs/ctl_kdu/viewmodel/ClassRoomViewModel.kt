package com.chs.ctl_kdu.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.chs.ctl_kdu.retrofitServices.CtlApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassRoomViewModel:ViewModel() {
    val api = CtlApi.create()

    fun getListView(course_id:String,class_no:String){
        api.goClassRoom("201008254671",course_id,class_no,"3").enqueue(object:Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    response.body()!!.string()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("Error",t.localizedMessage)
            }
        })
    }
}