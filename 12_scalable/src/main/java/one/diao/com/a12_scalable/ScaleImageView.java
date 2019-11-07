package one.diao.com.a12_scalable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;


/**
 * @author diaokaibin@gmail.com on 2019/2/2.
 */
public class ScaleImageView extends View {

    Bitmap mBitmap;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    GestureDetectorCompat mDetector;


    float WIDTH = Utils.dp2px(300);
    float originOffsetX, originOffsetY;
    float offsetX, offsetY;
    float smallScale;
    float bigScale;
    boolean big;
    //    float scaleFraction; // 0~1f
    float currentScale; // 0~1f


    ObjectAnimator scaleAnimator;

    float OVER_SCALE_FACTOR = 1.5f;

    HenGestureListener mHenGestureListener = new HenGestureListener();
    HenFlingRunner mFlingRunner = new HenFlingRunner();
    ScaleGestureDetector mScaleGestureDetector;
    HenScaleListener mHenScaleListener = new HenScaleListener();


    OverScroller mOverScroller;
//    Scroller mOverScroller;

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBitmap = Utils.getAvatar(getResources(), (int) WIDTH);
        mDetector = new GestureDetectorCompat(context, mHenGestureListener);
        mOverScroller = new OverScroller(context);
//        mOverScroller = new Scroller(context);
//        mDetector.setOnDoubleTapListener(this);

        // 关闭长按00000000000
//        mDetector.setIsLongpressEnabled(false);

        mScaleGestureDetector = new ScaleGestureDetector(context, mHenScaleListener);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originOffsetX = (getWidth() - WIDTH) / 2f;
        originOffsetY = (getHeight() - WIDTH) / 2f;

        if ((float) mBitmap.getWidth() / mBitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / mBitmap.getWidth();
            bigScale = (float) getHeight() / mBitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / mBitmap.getHeight();
            bigScale = (float) getWidth() / mBitmap.getWidth() * OVER_SCALE_FACTOR;
        }

        currentScale = smallScale;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // * scaleFraction 缩小的时候偏移会缩小 回到起始位置
        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
//        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
//        float scale = big ? bigScale : smallScale;
        canvas.scale(currentScale, currentScale, getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(mBitmap, originOffsetX, originOffsetY, mPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mScaleGestureDetector.onTouchEvent(event);
        if (!mScaleGestureDetector.isInProgress()) {
            result = mDetector.onTouchEvent(event);
        }
        return result;
    }


    private void fixOffset() {
        offsetX = Math.min(offsetX, (mBitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetX = Math.max(offsetX, (-mBitmap.getWidth() * bigScale - getWidth()) / 2);
        offsetY = Math.min(offsetY, (mBitmap.getHeight() * bigScale - getHeight()) / 2);
        offsetY = Math.max(offsetY, (-mBitmap.getHeight() * bigScale - getHeight()) / 2);
    }


    void refresh() {
        mOverScroller.computeScrollOffset();
        offsetX = mOverScroller.getCurrX();
        offsetY = mOverScroller.getCurrY();
        invalidate();
    }


    public float getCurrentSacle() {
        return currentScale;
    }

    public void setCurrentSacle(float scale) {
        this.currentScale = scale;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0, 1);
//            scaleAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//
//                    offsetX=0;
//                    offsetY=0;

//                }
//            });
        }

        scaleAnimator.setFloatValues(smallScale, bigScale);
        return scaleAnimator;

    }

    class HenGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (big) {
                mOverScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityX,
                        (int) (-(mBitmap.getWidth() * bigScale - getWidth()) / 2),
                        (int) ((mBitmap.getWidth() * bigScale - getWidth()) / 2),
                        (int) (-(mBitmap.getHeight() * bigScale - getHeight()) / 2),
                        (int) ((mBitmap.getHeight() * bigScale - getHeight()) / 2)
                );

//            for (int i = 10; i < 100; i += 10) {
//                postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refresh();
//                    }
//                },i);
//            }
                postOnAnimation(mFlingRunner);

            }
            return false;
        }

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

            if (big) {
                fixOffset();
                invalidate();
            }

            return false;
        }


        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            if (big) {

                offsetX = (e.getX() - getWidth() / 2f) - (e.getX() - getWidth() / 2f) * bigScale / smallScale;
                offsetY = (e.getY() - getHeight() / 2f) - (e.getY() - getHeight() / 2f) * bigScale / smallScale;
                fixOffset();
                fixOffset();
                getScaleAnimator().start();
            } else {
                getScaleAnimator().reverse();
            }
//        invalidate();
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }


    }

    class HenFlingRunner implements Runnable {

        @Override
        public void run() {
            if (mOverScroller.computeScrollOffset()) {
                mOverScroller.computeScrollOffset();
                offsetX = mOverScroller.getCurrX();
                offsetY = mOverScroller.getCurrY();
                invalidate();
                postOnAnimation(this);
            }

        }
    }


    class HenScaleListener implements ScaleGestureDetector.OnScaleGestureListener {


        float initScale;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            currentScale = initScale * detector.getScaleFactor(); //获取放大系数

            invalidate();
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {

            initScale = currentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
