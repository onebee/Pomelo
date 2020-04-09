package one.diao.com.a10_layout.second;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * @author diaokaibin@gmail.com on 2020/4/8.
 */
public class SquareImageView extends androidx.appcompat.widget.AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 上面已经拿到view 的尺寸了 下面拿到尺寸后 该一改  重新存

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        int size = Math.max(height, width);


        // 存一下
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
//
//    }
}
