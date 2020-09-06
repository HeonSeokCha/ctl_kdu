package com.chs.ctl_kdu.retrofitServices


import User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginServices {
    private val api =
        createRetrofit("https://ctl.kduniv.ac.kr").create(retrofitApi::class.java)
    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }
}