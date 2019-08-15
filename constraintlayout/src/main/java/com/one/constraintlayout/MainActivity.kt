package com.one.constraintlayout

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)


     val linearLayout =    findViewById<LinearLayout>(R.id.root)
        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).activities.forEach {
            activity ->
            if (activity.name == this::class.java.name) {
                return@forEach
            }

            val clazz = Class.forName(activity.name)

            val button = Button(this).apply {
                isAllCaps = false
                text = clazz.simpleName

                setOnClickListener {
                    startActivity(Intent(this@MainActivity,clazz))
                }

            }

            linearLayout.addView(button)



        }


    }
}
