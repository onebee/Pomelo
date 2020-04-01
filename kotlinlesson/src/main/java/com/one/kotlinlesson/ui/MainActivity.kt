package com.one.kotlinlesson.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.one.kotlinlesson.R
import com.one.kotlinlesson.entity.User
import com.one.kotlinlesson.utils.CacheUtils
import com.one.kotlinlesson.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var usernameKey = "username";
    private var passwordKey: String = "password"

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText

    private lateinit var codeView: CodeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)

        codeView = findViewById(R.id.code_view)

        et_username.setText("dddd徐")
        et_password.setText("存储发多少")

        val btn_login = findViewById<Button>(R.id.btn_login)

        btn_login.setOnClickListener(this)


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

        if (verify(user)) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)

            startActivity(Intent(this,LessonActivity::class.java))


        }
    }

    private fun verify(user: User): Boolean {
        if (user.username != null && user.username!!.length < 4) {
            Utils.toash("用户不合法")
            return false
        }

        if (user.password != null && user.password!!.length < 4) {
            Utils.toash("用户不合法")
            return false
        }

        return true;
    }

}
