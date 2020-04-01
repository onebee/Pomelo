package com.one.kotlinlearn1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val usernameKey = "username";
    private val passwordKey = "password";

    lateinit var et_username:EditText

//    var user:User = null


    var user2:User? = User()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_username=findViewById(R.id.et_username)

        et_username.setText("")

        var btn = findViewById<Button>(R.id.btn_login)
        btn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        if (v is Button) {

        }
    }
}
