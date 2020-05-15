package com.kohler.simple.motionlayoutsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_object.*
import kotlinx.android.synthetic.main.activity_object2.*
import kotlinx.android.synthetic.main.activity_object2.root

class Object2Activity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object2)

        btn2.setOnClickListener(this)
    }

    override fun onClick(v: View) {

//        v.animate().scaleX(2f).scaleY(2f).start()


        TransitionManager.beginDelayedTransition(v.parent as ViewGroup)

        val params = v.layoutParams as LinearLayout.LayoutParams

        params.height = params.height * 2
        params.width = params.width * 2

        v.layoutParams = params
    }
}
