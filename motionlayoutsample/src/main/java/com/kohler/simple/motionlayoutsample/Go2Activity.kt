package com.kohler.simple.motionlayoutsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.transition.Scene
import androidx.transition.TransitionManager

class Go2Activity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go2)

        bindData()
    }

    private fun bindData() {

        findViewById<ImageView>(R.id.iv_cover).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_cover).setImageResource(R.drawable.film_cover);
        findViewById<TextView>(R.id.tv_des).text = getString(R.string.film_description);

    }


    private var toggle = true
    override fun onClick(v: View) {

        val root = v.parent as ViewGroup

        val startScene = Scene.getSceneForLayout(root, R.layout.go2_start, this)
        val endScene = Scene.getSceneForLayout(root, R.layout.go2_end, this)

        if (toggle) {
            TransitionManager.go(endScene)
        } else {
            TransitionManager.go(startScene)
        }

        toggle = !toggle

        bindData()
    }


}
