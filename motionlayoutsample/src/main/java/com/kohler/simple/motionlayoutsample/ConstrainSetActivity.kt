package com.kohler.simple.motionlayoutsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_constrain_set_start.*

class ConstrainSetActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constrain_set_start)

        image_film_cover.setOnClickListener(this)
        rating_film_rating.rating = 4.7f
        text_film_title.text = getString(R.string.film_title)

        text_film_description.text = getString(R.string.film_description)

    }


    private var toggle = true
    override fun onClick(v: View) {

        var root = v.parent as ConstraintLayout
//        var fade = Fade()
//        fade.duration= 13000
        TransitionManager.beginDelayedTransition(root)

        val set = ConstraintSet()
        if (toggle) {
            set.clone(this, R.layout.activity_constrain_set_start)
        } else {
            set.clone(this, R.layout.activity_constrain_set_end)
        }

        set.applyTo(root)
        toggle = !toggle;

    }
}
