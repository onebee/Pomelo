package com.one.kotlinlearn1

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * @author  diaokaibin@gmail.com on 2020/3/6.
 */

class CodeView : AppCompatTextView {
    constructor(context:Context):this(context,null)

    constructor(context: Context,attrs: AttributeSet?):super(context,attrs){


        var ll = intArrayOf()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        dp2px(55f)
    }
}