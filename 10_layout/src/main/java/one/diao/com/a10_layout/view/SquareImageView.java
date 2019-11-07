package one.diao.com.a10_layout.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author diaokaibin@gmail.com on 2019/1/7.
 */
public class SquareImageView extends androidx.appcompat.widget.AppCompatImageView {

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        int size = Math.max(measuredHeight, measuredWidth);

        setMeasuredDimension(size,size);

    }

//    @Override
//    public void layout(int l, int t, int r, int b) {
//
//        int width = r-l;
//        int height = b-t;
//
//        int size = Math.max(width,height);
//        super.layout(l, t, l+size, t+size);
//    }
}
