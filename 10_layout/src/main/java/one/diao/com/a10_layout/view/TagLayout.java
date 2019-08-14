package one.diao.com.a10_layout.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 2019/1/7.
 */
public class TagLayout extends ViewGroup {


    private List<Rect> childBounds = new ArrayList<>();


    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthUsed =25;
        int heighUsed = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            measureChildWithMargins(childAt, widthMeasureSpec, widthUsed, heightMeasureSpec, heighUsed);

        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect rect = childBounds.get(i);
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }
}
