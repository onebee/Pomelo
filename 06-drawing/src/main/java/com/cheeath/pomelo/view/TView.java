package com.cheeath.pomelo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-11-06.
 */
public class TView extends View {
    Paint mPaint = new Paint();
    Path mPath = new Path();
    PathMeasure measure ;
    float length;

    float percentage;

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
        invalidate();
    }

    {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.moveTo(600, 300);
        mPath.lineTo(100, 300);
        mPath.lineTo(200, 600);
        mPath.lineTo(500, 600);
        mPath.lineTo(600, 300);
        measure = new PathMeasure(mPath, false);
        length = measure.getLength();
    }


    public TView(Context context) {
        super(context);
    }

    public TView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        PathEffect effect = getPath();
        mPaint.setPathEffect(effect);
        canvas.drawPath(mPath, mPaint);

    }

    private PathEffect getPath() {
        return new DashPathEffect(new float[]{length, length}, length - length*percentage);
    }

    ObjectAnimator animatorLine = ObjectAnimator.ofFloat(this, "percentage", 0.0f, 1.0f);

    public void start() {
        animatorLine.setDuration(2000).start();
    }
}
