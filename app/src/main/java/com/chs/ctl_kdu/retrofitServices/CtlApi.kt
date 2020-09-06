package com.chs.ctl_kdu.retrofitServices

import User
import okhttp3.ResponseBody
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.CookieManager
import java.net.CookiePolicy

interface CtlApi {
    @FormUrlEncoded
    @POST("login/doLogin.dunet")
    fun doLogin(
        @Field("user_id") userId: String,
        @Field("user_password") userPassword: String,
        @Field("group_cd") groupCode: String
    ): Call<User>

    @GET("lms/myLecture/doListView.dunet")
    fun doListView(
        @Query("mnid") mnid: String
    ): Call<ResponseBody>

    companion object {
        private const val BASE_URL: String = "https://ctl.kduniv.ac.kr"

        private val cookieManager = CookieManager()

        fun create(): CtlApi {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

            val client = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(cookieManager))
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CtlApi::class.java)
        }
    }
}