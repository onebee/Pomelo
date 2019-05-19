package com.one.constraintlayout

import android.content.Context
import android.os.Build
import android.support.constraint.ConstraintHelper
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.ViewAnimationUtils

class CircularRevealHelper(context: Context, attrs: AttributeSet) :
    ConstraintHelper(context, attrs) {

    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return

        for (referencedId in referencedIds) {
            val view = container!!.getViewById(referencedId)
            val radius = Math.hypot(view.width.toDouble(), view.height.toDouble()).toInt()

            ViewAnimationUtils.createCircularReveal(view, 0, 0, 0f, radius.toFloat())
                .setDuration(2000L)
                .start()
        }
    }
}
