package one.diao.com.a12_scalable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

/**
 * @author diaokaibin@gmail.com on 2019-08-14.
 */
public class ScaleImageView2 extends View {


    String TAG = "ScaleImageView2";
    // 放大系数
    public static final float OVER_SCALE_FACTOR = 1.5f;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float IMAGE_WIDTH = Utils.dp2px(200);
    private Bitmap bitmap;

    float originOffsetX;
    float originOffsetY;
    float smallScale;
    float bigScale;
    float currentScale; //0-1
    ObjectAnimator scaleAnimator;


    boolean big;
    GestureDetectorCompat mDetector;
    float offsetX;
    float offsetY;

    OverScroller mOverScroller;
    OneGestureDetectorListener mDetectorListener = new OneGestureDetectorListener();
    //    OneDoubleTapListener doubleTapListener;
    OneFilingRunnable filingRunnable = new OneFilingRunnable();
    ScaleGestureDetector scaleGestureDetector;

    OneScaleListener scaleListener = new OneScaleListener();

    public ScaleImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        mDetector = new GestureDetectorCompat(context, mDetectorListener);
//        doubleTapListener = new OneDoubleTapListener();
//        mDetector.setOnDoubleTapListener(doubleTapListener);
//        mDetector.setIsLongpressEnabled(false);
        mOverScroller = new OverScroller(context);
        scaleGestureDetector = new ScaleGestureDetector(context, scaleListener);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originOffsetX = (getWidth() - bitmap.getWidth()) / 2.0f;
        originOffsetY = (getHeight() - bitmap.getHeight()) / 2.0f;
        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {

            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        currentScale = smallScale;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = scaleGestureDetector.onTouchEvent(event);
        if (!scaleGestureDetector.isInProgress()) {
            result = mDetector.onTouchEvent(event);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);

//        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, mPaint);

    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }


    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0, 1);
        }

        scaleAnimator.setFloatValues(smallScale, bigScale);
        return scaleAnimator;
    }


    void refresh() {
        mOverScroller.computeScrollOffset();
        offsetX = mOverScroller.getCurrX();
        offsetY = mOverScroller.getCurrY();
        invalidate();
    }

    private void fixOffset() {
        offsetX = Math.min(offsetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetX = Math.max(offsetX, -(bitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetY = Math.min(offsetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
        offsetY = Math.max(offsetY, -(bitmap.getHeight() * bigScale - getHeight()) / 2);
    }


    class OneGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {


        /***
         * 确认这个事件是不是被消费了 一般情况返回true
         * @param
         * @return
         */
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        /***
         * 单击抬起
         * @param
         * @return
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {

            Log.i(TAG, " distanceX : " + distanceX);
            Log.i(TAG, " distanceY : " + distanceY);
            if (big) {
                offsetX -= distanceX;
                // 最大不能大于图片放大后的宽度减去屏幕宽度的一半, 所以取两者之间的小值
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
                mOverScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                        -(int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        (int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        -(int) (bitmap.getHeight() * bigScale - getHeight()) / 2,
                        (int) (bitmap.getHeight() * bigScale - getHeight()) / 2

                );

//            for (int i = 10; i < 100; i += 10) {
//                postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refresh();
//                    }
//                }, i);
//            }

                postOnAnimation(filingRunnable);


            }
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            if (big) {
                offsetX = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2f) * bigScale / smallScale;
                offsetY = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2f) * bigScale / smallScale;
                fixOffset();
                getScaleAnimator().setDuration(1000).start();
            } else {
                getScaleAnimator().setDuration(1000).reverse();
            }
//        invalidate();
            return false;
        }

    }

    /**********************************************************/


    class OneFilingRunnable implements Runnable {

        @Override
        public void run() {

            if (mOverScroller.computeScrollOffset()) {
                offsetX = mOverScroller.getCurrX();
                offsetY = mOverScroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }


        }
    }

    class OneScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

        float initialScale;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            currentScale = initialScale * detector.getScaleFactor();
            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialScale = currentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }

}
