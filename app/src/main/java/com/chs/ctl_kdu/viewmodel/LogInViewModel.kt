package com.chs.ctl_kdu.viewmodel

import User
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chs.ctl_kdu.retrofitServices.retrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class LogInViewModel:ViewModel(){
    val retrofit = Retrofit.Builder()
        .baseUrl("https://ctl.kduniv.ac.kr/")
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
    val service = retrofit.create(retrofitApi::class.java)

    fun Login(userId:String,userPw:String):LiveData<String>{
        var mutableData = MutableLiveData<String>()
        service.doLogin(userId,userPw,"UN").enqueue(object :Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                mutableData.value = response.headers()["Set-Cookie"]!!.split(";")[0]
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Failed",t.localizedMessage)
                mutableData.value = ""
            }
        })
        return mutableData
    }
}