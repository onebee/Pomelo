package one.diao.com.a10_layout.second;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 2020/4/9.
 */
public class TabLayout3 extends ViewGroup {


    List<Rect> childBounds = new ArrayList<>();

    public TabLayout3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthUsed = 0;
        int heightUsed = 0;

        int lineWidthUsed = 0;
        int lineMaxHeight = 0;


        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);


        for (int i = 0; i < getChildCount(); i++) {

            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (specMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.getMeasuredWidth() > specWidth) {
                // 换行
                lineWidthUsed = 0;
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            Rect childBound;

            if (childBounds.size() <= i) {
                childBound = new Rect();

                childBounds.add(childBound);
            } else {
                childBound = childBounds.get(i);
            }

            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(),
                    heightUsed + child.getMeasuredHeight()
            );

            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());


        }
        int width = widthUsed;
        int height = heightUsed + lineMaxHeight;
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect rect = childBounds.get(i);
            child.layout(rect.left, rect.top, rect.right, rect.bottom);

        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
