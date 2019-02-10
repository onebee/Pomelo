package one.diao.com.view_drag_helper_sample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author diaokaibin@gmail.com on 2019/2/10.
 */
public class VDHLayout extends LinearLayout {

    ViewDragHelper mViewDragHelper;

    View mDrugView;
    View mAutoBackView;
    View mEdgeTrackerView;

    Point mAutoBackOriginPos = new Point();

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            return mDrugView == view || view == mAutoBackView;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {

            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            if (child == mDrugView) {
                if (child.getBottom() > mAutoBackOriginPos.y) {
                    mAutoBackView.setBackgroundColor(Color.BLUE);
                } else {
                    mAutoBackView.setBackgroundColor(Color.RED);
                }
            }
            return top;
        }

        // 手指释放时候的回掉
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            if (releasedChild == mAutoBackView) {
                mViewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                postInvalidateOnAnimation();
            }

        }



        // 在边界拖动的时候回掉
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mViewDragHelper.captureChildView(mEdgeTrackerView,pointerId);

        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            return getMeasuredWidth()-child.getMeasuredWidth();
        }


        @Override
        public int getViewVerticalDragRange(@NonNull View child) {
            return getMeasuredHeight()-child.getMeasuredHeight();
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);

        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        @Override
        public boolean onEdgeLock(int edgeFlags) {
            return super.onEdgeLock(edgeFlags);
        }

        @Override
        public int getOrderedChildIndex(int index) {
            return super.getOrderedChildIndex(index);
        }
    };


    public VDHLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mViewDragHelper = ViewDragHelper.create(this, 1.0f, callback);
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mDrugView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
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
            postInvalidateOnAnimation();
        }

    }
}
