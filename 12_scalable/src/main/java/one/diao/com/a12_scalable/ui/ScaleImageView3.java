package one.diao.com.a12_scalable.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import one.diao.com.a12_scalable.Utils;

/**
 * @author diaokaibin@gmail.com on 2020/4/27.
 * <p>
 * 第三遍复习
 */
public class ScaleImageView3 extends View {

    private static final float IMAGE_WIDTH = Utils.dp2px(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap mBitmap;


    float originOffsetX;
    float originOffsetY;

    float offsetX;
    float offsetY;

    float smallScale;
    float bigScale;

    GestureDetectorCompat mGestureDetectorCompat;
    ScaleGestureDetector mScaleGestureDetector;


    boolean big;


    OneGestureListener mGestureListener = new OneGestureListener();

    OneScaleListener mScaleListener = new OneScaleListener();


    ObjectAnimator scaleAnimator;


    OverScroller mScroller;

    //    float scaleFraction;  //0 - 1
    float currentScale;


    private float getCurrentScale() {
        return currentScale;
    }

    private void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0, 1);
            scaleAnimator.setInterpolator(new LinearInterpolator());
//            scaleAnimator.setDuration(2000);
        }

        scaleAnimator.setFloatValues(smallScale, bigScale);

        return scaleAnimator;

    }


    public ScaleImageView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        mGestureDetectorCompat = new GestureDetectorCompat(context, mGestureListener);
        mScroller = new OverScroller(context);
        mScaleGestureDetector = new ScaleGestureDetector(context, mScaleListener);

//        mGestureDetectorCompat.setOnDoubleTapListener(this);
//        mGestureDetectorCompat.setIsLongpressEnabled();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originOffsetX = ((float) getWidth() - mBitmap.getWidth()) / 2;
        originOffsetY = ((float) getHeight() - mBitmap.getHeight()) / 2;


        if ((float) mBitmap.getWidth() / mBitmap.getHeight() > (float) getWidth() / getHeight()) {

            smallScale = ((float) getWidth() / mBitmap.getWidth());
            bigScale = ((float) getHeight() / mBitmap.getHeight()) * OVER_SCALE_FACTOR;
        } else {
            smallScale = ((float) getHeight() / mBitmap.getHeight());
            bigScale = ((float) getWidth() / mBitmap.getWidth()) * OVER_SCALE_FACTOR;
        }

        currentScale =smallScale;


    }

    @Override
    protected void onDraw(Canvas canvas) {


        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);

        // 乘上 scaleFraction 的目的是 缩小的时候 把偏移量恢复
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);

//        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
//        float scale = currentScale;
        canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(mBitmap, originOffsetX, originOffsetY, mPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        boolean result = mScaleGestureDetector.onTouchEvent(event);
        if (!mScaleGestureDetector.isInProgress()) {
            result = mGestureDetectorCompat.onTouchEvent(event);
        }
        return result;
    }


    private void fixOffset() {
        offsetX = Math.min(offsetX, (mBitmap.getWidth() * bigScale - getWidth()) / 2);
        // 最小值不能小于
        offsetX = Math.max(offsetX, -(mBitmap.getWidth() * bigScale - getWidth()) / 2);


        offsetY = Math.min(offsetY, (mBitmap.getHeight() * bigScale - getHeight()) / 2);
        // 最小值不能小于
        offsetY = Math.max(offsetY, -(mBitmap.getHeight() * bigScale - getHeight()) / 2);
    }


    @Override
    public void computeScroll() {

        if (mScroller.computeScrollOffset()) {

        }

    }


    class OneGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            /**
             * 注意这里的 减号
             */
            if (big) {
                offsetX -= distanceX;


                // 边界处理 最大值不能大于
                offsetY -= distanceY;
                fixOffset();

                invalidate();
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent down, MotionEvent event, float velocityX, float velocityY) {

            if (big) {
                mScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                        (int) (-(mBitmap.getWidth() * bigScale - getWidth()) / 2),
                        (int) ((mBitmap.getWidth() * bigScale - getWidth()) / 2),
                        (int) (-(mBitmap.getHeight() * bigScale - getHeight()) / 2),
                        (int) ((mBitmap.getHeight() * bigScale - getHeight()) / 2)

                );
            }

            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            if (big) {
                // 点击的时候 放大的 偏移量修正
                offsetX = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2F) * bigScale / smallScale;
                offsetY = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2F) * bigScale / smallScale;
                fixOffset();
                getScaleAnimator().start();

            } else {
                getScaleAnimator().reverse();
            }
//        invalidate();
            return false;
        }
    }


    class OneScaleListener implements ScaleGestureDetector.OnScaleGestureListener {


        float initialScale;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            currentScale = initialScale*detector.getScaleFactor();

            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialScale =currentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
