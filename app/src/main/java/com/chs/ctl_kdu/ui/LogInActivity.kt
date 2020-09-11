package com.chs.ctl_kdu.ui

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chs.ctl_kdu.R
import com.chs.ctl_kdu.viewmodel.LogInViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LogInActivity : AppCompatActivity() {
    private lateinit var viewmodel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewmodel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(Application())
            .create(LogInViewModel::class.java)

        btn_do_login.setOnClickListener {
            if(input_userId.text!!.trim().isNotEmpty() && input_userPw.text!!.trim().isNotEmpty()){
                doLogin(input_userId.text!!.trim().toString(),input_userPw.text!!.trim().toString())
            } else{
                Toast.makeText(this, "아이디와 비밀번호를 입력해주세요!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doLogin(userId: String, userPw: String) {
        viewmodel.Login(userId, userPw).observe(this, Observer { result ->
            if (result["userName"]?.isNotEmpty()!!) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userName",result["userName"])
                intent.putExtra("userNo",result["userNo"])
                intent.putExtra("deptNm",result["deptNm"])
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login Failure!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}