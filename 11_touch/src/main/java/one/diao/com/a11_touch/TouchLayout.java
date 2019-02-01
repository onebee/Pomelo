package one.diao.com.a11_touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author diaokaibin@gmail.com on 2019/1/30.
 */
public class TouchLayout extends ViewGroup {
    public TouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    // 除了拦截事件之前 还为onTouchEvent 记录点击的位置
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {


        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return super.shouldDelayChildPressedState();
    }

}
