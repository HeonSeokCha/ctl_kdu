package com.chs.ctl_kdu.retrofitServices

import User
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface retrofitApi {
    @FormUrlEncoded
    @POST("login/doLogin.dunet")
    fun doLogin(
        @Field("user_id") userId: String,
        @Field("user_password") userPassword: String,
        @Field("group_cd") groupCode: String
    ): Call<User>

    @POST("/lms/myLecture/doListView.dunet?mnid=201008840728")
    fun goList(@Header("Cookie") cookie:String):Call<ResponseBody>
}