package one.diao.com.a10_layout.second;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import one.diao.com.a10_layout.Utils;

/**
 * @author diaokaibin@gmail.com on 2020/4/8.
 */
public class CircleView extends View {

    Paint mPaint = new Paint();


    public static final int RADIUS = (int) Utils.dp2px(80);
    public static final int PADDING = (int) Utils.dp2px(30);


    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  自己算 不要这个

        int width = (RADIUS + PADDING) * 2;
        int height = (RADIUS + PADDING) * 2;
        width = resolveSizeAndState(width,widthMeasureSpec,0);
        height = resolveSizeAndState(height, heightMeasureSpec, 0);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.drawCircle(RADIUS + PADDING, RADIUS + PADDING, RADIUS, mPaint);
    }
}
