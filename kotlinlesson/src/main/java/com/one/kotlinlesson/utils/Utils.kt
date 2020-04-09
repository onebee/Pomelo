/**
 * 自定义 Java 里面调用的类名.
 */
@file:JvmName("DpUtils")

package com.one.kotlinlesson.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.one.kotlinlesson.BaseApplication

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */

private val displayMetrics = Resources.getSystem().displayMetrics

fun Float.dp2px(): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)


fun Activity.log(s: String) {
    Log.e("activity", s)
}

fun Context.log(s: String) {
    Log.e("context", s)
}


val ViewGroup.firstChild: View
    get() = getChildAt(0)


object Utils {

    /**
     * 在java 里面调用可以通过 类名.的方式调用
     */
//    @JvmStatic
//    fun toash(string: String?) {
//        toash(string, Toast.LENGTH_LONG)
//
//    }

    @JvmOverloads
    @JvmStatic
    fun toash(string: String?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(BaseApplication.currentApplication, string, duration).show()
    }
}


