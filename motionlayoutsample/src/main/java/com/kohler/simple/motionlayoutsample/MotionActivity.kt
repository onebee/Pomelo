package com.kohler.simple.motionlayoutsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_constrain_set_start.*

class MotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_montion)

        rating_film_rating.rating = 4.7f
        text_film_title.text = getString(R.string.film_title)

        text_film_description.text = getString(R.string.film_description)

    }
}
