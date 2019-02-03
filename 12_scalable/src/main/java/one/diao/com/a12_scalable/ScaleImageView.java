package one.diao.com.a12_scalable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;


/**
 * @author diaokaibin@gmail.com on 2019/2/2.
 */
public class ScaleImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

    Bitmap mBitmap;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    GestureDetectorCompat mDetector;


    float WIDTH = Utils.dp2px(300);
    float originOffsetX, originOffsetY;
    float offsetX, offsetY;
    float smallScale;
    float bigScale;
    boolean big;
    float scaleFraction; // 0~1f

    ObjectAnimator scaleAnimator;

    float OVER_SCALE_FACTOR = 1.5f;

//    OverScroller mOverScroller;
    Scroller mOverScroller;

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBitmap = Utils.getAvatar(getResources(), (int) WIDTH);
        mDetector = new GestureDetectorCompat(context, this);
//        mOverScroller = new OverScroller(context);
        mOverScroller = new Scroller(context);
//        mDetector.setOnDoubleTapListener(this);

        // 关闭长按
//        mDetector.setIsLongpressEnabled(false);

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
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(offsetX, offsetY);
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
//        float scale = big ? bigScale : smallScale;
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(mBitmap, originOffsetX, originOffsetY, mPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
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
            offsetX -= distanceX;
            offsetX = Math.min(offsetX, (mBitmap.getWidth() * bigScale - getWidth()) / 2);
            offsetX = Math.max(offsetX, (-mBitmap.getWidth() * bigScale - getWidth()) / 2);
            offsetY -= distanceY;
            offsetY = Math.min(offsetY, (mBitmap.getHeight() * bigScale - getHeight()) / 2);
            offsetY = Math.max(offsetY, (-mBitmap.getHeight() * bigScale - getHeight()) / 2);
            invalidate();
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

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
            postOnAnimation(this);

        }
        return false;
    }

    void refresh() {
        mOverScroller.computeScrollOffset();
        offsetX = mOverScroller.getCurrX();
        offsetY = mOverScroller.getCurrY();
        invalidate();
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        big = !big;
        if (big) {
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


    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return scaleAnimator;

    }

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
