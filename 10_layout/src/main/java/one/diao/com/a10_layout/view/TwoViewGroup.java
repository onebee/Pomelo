package one.diao.com.a10_layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author diaokaibin@gmail.com on 2019/1/7.
 */
public class TwoViewGroup extends ViewGroup {




    public TwoViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

//        layout();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


//        measure(widthMeasureSpec,heightMeasureSpec);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}
