package com.kohler.simple.motionlayoutsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_object.*

class ObjectActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        iv.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        val params = v.layoutParams as FrameLayout.LayoutParams

        params.gravity = Gravity.END

        v.layoutParams = params
    }
}
