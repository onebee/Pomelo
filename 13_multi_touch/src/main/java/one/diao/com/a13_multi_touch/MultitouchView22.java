package one.diao.com.a13_multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-08-26.
 * <p>
 * 多点触控 配合型
 */
public class MultitouchView22 extends View {


    private static final int IMAGE_WIDTH = (int) Utils.dp2px(150);
    private final Bitmap mBitmap;

    float downX, downY;
    float originOffsetX, originOffsetY;
    float offsetX, offsetY;

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MultitouchView22(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Utils.getAvatar(getResources(), IMAGE_WIDTH);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean isPointerUP = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;

        float sumX = 0;
        float sumY = 0;
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            if (!(isPointerUP && i == event.getActionIndex())) {
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }

        if (isPointerUP) {
            pointerCount -= 1;
        }

        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:

                downX = focusX;
                downY = focusY;
                originOffsetX = offsetX;
                originOffsetY = offsetY;

                break;


            case MotionEvent.ACTION_MOVE:

                offsetX = focusX + originOffsetX - downX;
                offsetY = focusY + originOffsetY - downY;

                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);

    }

}
