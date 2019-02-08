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
public class MultitouchView3 extends View {

    private static final String TAG = "MultitouchView1";

    private static final float IMAGE_WIDTH = Utils.dp2px(200);
    private static final float WIDTH = 100;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    float offsetX, offsetY;
    float originOffsetX, originOffsetY;
    float downX, downY;

    int index;
    int trackingPointerId;


    public MultitouchView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;
        boolean isPointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;

        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {

            if (!(isPointerUp && i == event.getActionIndex())) {

                sumX += event.getX(i);
                sumY += event.getY(i);
            }

        }


        if (isPointerUp) {
            pointerCount -= 1;
        }
        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;


        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:

                trackingPointerId = event.getPointerId(0);
                downX = focusX;
                downY = focusY;

                originOffsetX = offsetX;
                originOffsetY = offsetY;
                Log.d(TAG, "downX ： " + downX + "  downY ：" + downY);
                Log.d(TAG, "originOffsetX ： " + originOffsetX + "  originOffsetY ：" + originOffsetY);

                break;

            case MotionEvent.ACTION_MOVE:

                offsetX = originOffsetX + focusX - downX;
                offsetY = originOffsetY + focusY - downY;

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
