package com.cheeath.pomelo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cheeath.pomelo.Utils;

/**
 * @author diaokaibin@gmail.com on 2018/11/6.
 */
public class PieChart extends View {


    float RADIU = Utils.dp2px(120);
    int LENGTH = (int) Utils.dp2px(20);
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    RectF bounds = new RectF();

    int[] colors = {Color.parseColor("#2979FF"), Color.parseColor("#C2185B"),
            Color.parseColor("#009688"), Color.parseColor("#FF8F00")};

    int[] angle = {60, 100, 120, 80};


    {


    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bounds.set(getWidth() / 2 - RADIU, getHeight() / 2 - RADIU, getWidth() / 2 + RADIU, getHeight() / 2 + RADIU);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for (int i = 0; i < angle.length; i++) {
            mPaint.setColor(colors[i]);
            canvas.save();
            if (i == 2) {
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angle[i] / 2)) * LENGTH,

                        (float) Math.sin(Math.toRadians(currentAngle + angle[i] / 2)) * LENGTH);
            }

            canvas.drawArc(bounds, currentAngle, angle[i], true, mPaint);
            canvas.restore();
            currentAngle += angle[i];
        }

    }
}
