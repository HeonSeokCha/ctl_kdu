package com.chs.ctl_kdu.viewmodel

import User
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chs.ctl_kdu.retrofitServices.CtlApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInViewModel:ViewModel(){
    private val api = CtlApi.create()

    fun Login(userId: String, userPw: String): LiveData<Boolean> {
        val ret = MutableLiveData<Boolean>()
        api.doLogin(userId, userPw, "UN").enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                ret.value = true
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Failed", t.localizedMessage)
                ret.value = false
            }
        })
        return ret
    }
}