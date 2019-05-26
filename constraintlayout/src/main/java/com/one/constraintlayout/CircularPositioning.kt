package com.one.constraintlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.support.constraint.ConstraintLayout
import android.view.animation.LinearInterpolator


class CircularPositioning : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_positioning)

        var earth = findViewById<ImageView>(R.id.earth)
        var moon = findViewById<ImageView>(R.id.moon)

        val earthAnimator = ValueAnimator.ofFloat(0f, 1f).apply {

            duration = 100000
            repeatCount = INFINITE
            interpolator = LinearInterpolator()

        }

        val moonAnimator = ValueAnimator.ofFloat(0F, 1F).apply {

            duration = 20000
            repeatCount = INFINITE
            interpolator = LinearInterpolator()
        }

        earthAnimator.addUpdateListener {
            val params = earth.layoutParams as ConstraintLayout.LayoutParams
            params.circleAngle = 0 + it.animatedFraction * 360
            moon.requestLayout()

        }

        moonAnimator.addUpdateListener {
            val params = moon.layoutParams as ConstraintLayout.LayoutParams
            params.circleAngle = 270 + it.animatedFraction * 360
            moon.requestLayout()

        }
        findViewById<ImageView>(R.id.sun).setOnClickListener {
            earthAnimator.start()
            moonAnimator.start()
        }

    }
}
