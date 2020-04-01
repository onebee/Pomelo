/**
 * 自定义 Java 里面调用的类名.
 */
@file:JvmName("DpUtils")
package com.one.kotlinlesson.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.one.kotlinlesson.BaseApplication

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */

private val displayMetrics = Resources.getSystem().displayMetrics

fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)

}


object Utils {

    /**
     * 在java 里面调用可以通过 类名.的方式调用
     */
    @JvmStatic
    fun toash(string: String?) {
        toash(string, Toast.LENGTH_LONG)

    }

    fun toash(string: String?, duration: Int) {
        Toast.makeText(BaseApplication.currentApplication(), string, duration).show()


    }
}


