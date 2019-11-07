package one.diao.com.view_drag_helper_sample;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author diaokaibin@gmail.com on 2019/2/9.
 */
public class MyViewDragHelper extends FrameLayout {

    ViewDragHelper mViewDragHelper;

    View menuView, mainView;

    int width;


    public MyViewDragHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 将触摸事件传递给ViewDragHelper
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        // 什么时候开始检测触摸事件
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            // 当前触摸的child 是 mainView 的时候开始检测
            // 根据传入的View 参数 来决定view 是否可以 拖动

            return mainView == view;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            Log.d("------", " left - " + left);
            if (left > 250) {
                left =250;
            }
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            Log.d("------", " top - " + top);
            Log.d("------", " width - " + getWidth());
            Log.d("------", " height - " + getHeight());
            return top;
        }


        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (mainView.getLeft() < (getWidth() / 2)) {
                // 关闭菜单
                mViewDragHelper.smoothSlideViewTo(mainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(MyViewDragHelper.this);
            } else {
                mViewDragHelper.smoothSlideViewTo(mainView, (int) Utils.dpToPixel(300), 0);
                ViewCompat.postInvalidateOnAnimation(MyViewDragHelper.this);
            }
        }
    };
}
