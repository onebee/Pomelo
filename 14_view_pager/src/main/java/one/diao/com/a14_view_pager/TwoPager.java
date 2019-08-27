package one.diao.com.a14_view_pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author diaokaibin@gmail.com on 2019-08-26.
 * 手写ViewPager
 */
public class TwoPager extends ViewGroup {


    public TwoPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

}
