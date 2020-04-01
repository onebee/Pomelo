package com.one.kotlinlearn1

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author  diaokaibin@gmail.com on 2020/3/6.
 */

private val displayMetrics = Resources.getSystem().displayMetrics

fun dp2px(dp:Float):Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, displayMetrics)
}

