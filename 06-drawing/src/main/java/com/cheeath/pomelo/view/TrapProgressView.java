package com.cheeath.pomelo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * created by liming
 */
public class TrapProgressView extends View {

    private static final float MAX_PROGRESS = 10f;
    private static final float ANGLE = 10f;
    private float lineXTMaxProgress;
    private float lineXBMaxProgress;
    private float lineYMaxProgress;
    private float offsetX;

    private Paint paint;
    private float progress;
    private Path path;

    public TrapProgressView(Context context) {
        super(context);
        init();
    }

    public TrapProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TrapProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paint = new TextPaint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(40f);
        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeCap(Paint.Cap.ROUND);

        path = new Path();

        calculateLineProgress();
    }

    private void calculateLineProgress() {
        lineXTMaxProgress = MAX_PROGRESS * 5 / 11;
        lineXBMaxProgress = MAX_PROGRESS * 4 / 11;
        lineYMaxProgress = (MAX_PROGRESS - lineXTMaxProgress - lineXBMaxProgress) / 2;

        Log.d("TT", lineXTMaxProgress + " , " + lineXBMaxProgress + " , " + lineYMaxProgress);
    }

    private void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();

        float topProgress = 0;
        float leftProgress = 0;
        float bottomProgress = 0;
        float rightProgress = 0;

        if (progress <= lineXTMaxProgress) {
            topProgress = progress;
        } else if (progress <= lineXTMaxProgress + lineYMaxProgress) {
            topProgress = lineXTMaxProgress;
            leftProgress = progress - lineXTMaxProgress;
        } else if (progress <= lineXTMaxProgress + lineYMaxProgress + lineXBMaxProgress) {
            topProgress = lineXTMaxProgress;
            leftProgress = lineYMaxProgress;
            bottomProgress = progress - lineXTMaxProgress - lineYMaxProgress;
        } else if (progress <= MAX_PROGRESS) {
            topProgress = lineXTMaxProgress;
            leftProgress = lineYMaxProgress;
            bottomProgress = lineXBMaxProgress;
            rightProgress = progress - lineXTMaxProgress - lineYMaxProgress - lineXBMaxProgress;
        }

//        canvas.drawLine(0,0, getWidth(), getHeight(), paint);
//        canvas.drawLine(0,getHeight() / 2, getWidth(), getHeight() / 2, paint);

//        path.moveTo(0,0);
//        path.lineTo(getWidth(), getHeight());
//        path.moveTo(0, getHeight() / 2);
//        path.lineTo(getWidth(), getHeight() /2);
//        canvas.drawPath(path, paint);

        float height = getHeight() - getPaddingTop() - getPaddingBottom();
        float width = getWidth() - getPaddingLeft() - getPaddingRight();

//        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        Log.d("TT", "w = " + width + ", h = " + height);

        float rightTopX = getWidth() - getPaddingRight();
        float rightTopY = getPaddingTop();

        float tan = (float) Math.abs(Math.tan(ANGLE));
        offsetX = height * tan;
        if (topProgress > 0) {
            path.moveTo(rightTopX, rightTopY);
            path.lineTo(width - width / lineXTMaxProgress * topProgress, rightTopY);
        }
        if (leftProgress > 0) {
            float y = height / lineYMaxProgress * leftProgress + getPaddingTop();
            float x = offsetX / lineYMaxProgress * leftProgress + getPaddingLeft();
            path.rLineTo( x, y );
            Log.d("TT", " left ==> x = " + x + " , y = " + y);
        }
        if (bottomProgress > 0) {
            float x = (width - offsetX * 2) * bottomProgress / lineXBMaxProgress + getPaddingLeft() + offsetX;
            float y = getPaddingTop() + height;
            path.rLineTo(  x , y);
            Log.d("TT", " bottom ==> x = " + x + " , y = " + y);
        }
        if (rightProgress > 0) {
            float y = height - height * rightProgress / lineYMaxProgress + getPaddingTop();
            float x = (width - offsetX * 2) + (y * tan) + + getPaddingLeft();
            path.rLineTo(x , y );
        }

        canvas.drawPath(path, paint);
    }

    public void start(final long millTime, final OnProgressFinishListener listener) {
        final ValueAnimator animator = ValueAnimator.ofFloat(0, MAX_PROGRESS).setDuration(millTime);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                long time = animation.getCurrentPlayTime();
                setProgress(progress);
                if (progress >= MAX_PROGRESS && time >= millTime) {
                    listener.onProgressFinished();
                }
            }
        });
        animator.start();
    }

    public interface OnProgressFinishListener {
        void onProgressFinished();
    }

}
