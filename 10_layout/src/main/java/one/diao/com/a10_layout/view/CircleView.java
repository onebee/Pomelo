package one.diao.com.a10_layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import one.diao.com.a10_layout.Utils;

/**
 * @author diaokaibin@gmail.com on 2019/1/7.
 */
public class CircleView extends View {

    public static final int RADIUS = (int) Utils.dp2px(55);
    public static final int PADDING = (int) Utils.dp2px(5);

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = (RADIUS +PADDING)*2;
        int height = (RADIUS +PADDING)*2;

        width = resolveSize(width, widthMeasureSpec);
        height = resolveSize(height, heightMeasureSpec);
        setMeasuredDimension(width,height);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);

        canvas.drawCircle(RADIUS+PADDING,RADIUS+PADDING,RADIUS,mPaint);

    }
}
