package com.one.kotlinlesson.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.one.kotlinlesson.R
import com.one.kotlinlesson.entity.User
import com.one.kotlinlesson.utils.CacheUtils
import com.one.kotlinlesson.utils.Utils
import com.one.kotlinlesson.utils.log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var usernameKey = "username";
    private var passwordKey: String = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username.setText("dddd徐")
        et_password.setText("存储发多少")
        btn_login.setOnClickListener(this)

        this.log("2222")
        (this as Context).log("555")

    }

    override fun onClick(v: View?) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString();
        val code = et_code.text.toString()
        val user = User(username, password, code)


        fun verify(): Boolean {
            if (user.username?.length ?: 0 < 4) {
                Utils.toash("用户不合法")
                Utils.toash("hhh", Toast.LENGTH_LONG)
                return false
            }
            if (user.password != null && user.password!!.length < 4) {
                Utils.toash("用户不合法")
                return false
            }
            return true
        }

        if (verify()) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }


}
