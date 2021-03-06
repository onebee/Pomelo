package com.one.kotlinlesson.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import com.one.kotlinlesson.R
import com.one.kotlinlesson.utils.dp2px
import kotlin.random.Random

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 *
 */
class CodeView constructor(context: Context, attrs: AttributeSet?=null)  : androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    private var codeList = arrayOf(
            "Kotlin",
            "android",
            "java",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip"
    )

    private var paint = Paint()
    // 调用了父类的构造器, 类名后面就不需要加括号了
    init {

        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE;
        paint.color = getContext().getColor(R.color.colorAccent)
        paint.strokeWidth = 6f.dp2px()
        updateCode()

    }

    fun updateCode() {
        val random = Random.nextInt(codeList.size)
        val code = codeList[random]
        text = code

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(0f,height.toFloat(),width.toFloat(),0.toFloat(),paint)
        super.onDraw(canvas)

    }


}