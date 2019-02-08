package one.diao.com.a13_multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019/2/6.
 */
public class MultitouchView1 extends View {

    private static final String TAG = "MultitouchView1";

    private static final float IMAGE_WIDTH = Utils.dp2px(200);
    private static final float WIDTH = 100;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    float offsetX, offsetY;
    float originOffsetX, originOffsetY;
    float downX, downY;


    public MultitouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();

                originOffsetX = offsetX;
                originOffsetY = offsetY;
                Log.d(TAG, "downX ： " + downX + "  downY ：" + downY);
                Log.d(TAG, "originOffsetX ： " + originOffsetX + "  originOffsetY ：" + originOffsetY);

                break;

            case MotionEvent.ACTION_MOVE:

                offsetX = originOffsetX + event.getX() - downX;
                offsetY = originOffsetY + event.getY() - downY;

                Log.d(TAG, "offsetX ： " + offsetX + "  offsetY ：" + offsetY);
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
        for (int i = 1; i < 20; i++) {
            canvas.drawLine(WIDTH * i, 0, WIDTH * i, getHeight(), paint);
            canvas.drawLine(0, WIDTH * i, getWidth(), WIDTH * i, paint);

        }
    }
}
