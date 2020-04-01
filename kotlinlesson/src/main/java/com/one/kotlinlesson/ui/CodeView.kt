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
class CodeView : androidx.appcompat.widget.AppCompatTextView {


    constructor(context: Context) : this(context, null)

    // 调用了父类的构造器, 类名后面就不需要加括号了
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)

        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE;
        paint.color = getContext().getColor(R.color.colorAccent)
        paint.strokeWidth = dp2px(6f)


        updateCode()

    }

    private var paint = Paint()
    private var codeList = arrayOf(
        "Kotlin",
        "android",
        "java",
        "https",
        "okhttp",
        "retrofit",
        "tcp/ip"
    )


    fun updateCode() {

        var random = Random.nextInt(codeList.size)
        val code = codeList[random]
        text = code


    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(0f,height.toFloat(),width.toFloat(),0.toFloat(),paint)
        super.onDraw(canvas)

    }


}