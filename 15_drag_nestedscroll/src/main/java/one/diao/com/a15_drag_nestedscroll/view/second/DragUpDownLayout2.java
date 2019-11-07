package one.diao.com.a15_drag_nestedscroll.view.second;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import one.diao.com.a15_drag_nestedscroll.R;

/**
 * @author diaokaibin@gmail.com on 2019-08-29.
 */
public class DragUpDownLayout2 extends FrameLayout {

    View mView;
    ViewDragHelper mViewDragHelper;
    ViewConfiguration mConfiguration;
    ViewDragHelper.Callback mCallback;

    public DragUpDownLayout2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mCallback = new DragListener();
        mViewDragHelper = ViewDragHelper.create(this, mCallback);
        mConfiguration = new ViewConfiguration();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mView = findViewById(R.id.view);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
//            ViewCompat.postInvalidateOnAnimation(this);
            postInvalidateOnAnimation();
        }

    }

    class DragListener extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            return mView == view;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            int minimumFlingVelocity = mConfiguration.getScaledMinimumFlingVelocity();
            Log.d("----"," yvel : " + yvel + " ------ minimumFlingVelocity : " + minimumFlingVelocity);
            if (Math.abs(yvel) > mConfiguration.getScaledMinimumFlingVelocity()) {
                if (yvel > 0) {
                    mViewDragHelper.settleCapturedViewAt(0, getHeight() - releasedChild.getHeight());
                } else {
                    mViewDragHelper.settleCapturedViewAt(0, 0);
                }

            } else {

                if (releasedChild.getTop() < getHeight() - releasedChild.getBottom()) {
                    mViewDragHelper.settleCapturedViewAt(0, 0);
                } else {
                    mViewDragHelper.settleCapturedViewAt(0, getHeight() - releasedChild.getHeight());
                }

            }

            postInvalidateOnAnimation();

        }
    }
}
