package com.kohler.simple.motionlayoutsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_object.setOnClickListener {
            startActivity(Intent(this, ObjectActivity::class.java))
        }

        btn_object2.setOnClickListener {
            startActivity(Intent(this, Object2Activity::class.java))
        }

        btn_go.setOnClickListener {
            startActivity(Intent(this, GoActivity::class.java))

        }

        btn_go2.setOnClickListener {
            startActivity(Intent(this, Go2Activity::class.java))

        }

        btn_set.setOnClickListener {
        startActivity(Intent(this, ConstrainSetActivity::class.java))

        }
    }
}
