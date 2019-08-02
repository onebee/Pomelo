package one.diao.com.a10_layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author diaokaibin@gmail.com on 2019/1/7.
 */
public class TagLayout extends ViewGroup {


    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (i == 0) {

                child.layout(l, t, (l + r) / 2, (b + t) / 2);
            } else {

                child.layout((l + r) / 2, (b + t) / 2, r, b);
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
