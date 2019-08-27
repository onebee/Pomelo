package one.diao.com.a14_view_pager;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.OverScroller;

/**
 * @author diaokaibin@gmail.com on 2019-08-27.
 */
public class ScrollerLayout extends ViewGroup {


    OverScroller mScroller;

    // 拖动的最小间距
    int mTouchSlop;

    // 按下去的屏幕坐标
    float downX;


    // 当前move 的坐标
    float moveX;

    // 上次Move 的时候 坐标
    float lastMoveX;

    int leftBorder;

    int rightBorder;


    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScroller = new OverScroller(context, new BounceInterpolator());
        ViewConfiguration config = new ViewConfiguration();
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(config);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(
                    child.getMeasuredWidth() * i,
                    0,
                    child.getMeasuredWidth() * (i + 1),
                    child.getMeasuredHeight());
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                downX = ev.getRawX();
                lastMoveX = downX;

                break;

            case MotionEvent.ACTION_MOVE:

                moveX = ev.getRawX();
                float diff = Math.abs(moveX - downX);
                lastMoveX = moveX;
                if (diff > mTouchSlop) {
                    return  true;
                }

                break;


        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);

    }

    @Override
    public void computeScroll() {
        super.computeScroll();

    }
}
