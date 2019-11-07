package com.one.constraintlayout

import android.os.Bundle
import androidx.constraintlayout.widget.Placeholder
import androidx.appcompat.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup

class PlaceHolder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_holder)



    }

   fun  click(view:View){

       TransitionManager.beginDelayedTransition(view.parent as ViewGroup)

       findViewById<Placeholder>(R.id.place_holder).setContentId(view.id)

    }
}
