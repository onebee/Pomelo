package one.diao.com.a13_multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-08-26.
 * 接力型多点触控
 */
public class MultitouchView21 extends View {


    private static final int IMAGE_WIDTH = (int) Utils.dp2px(150);
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Bitmap mBitmap;


    float offsetX;
    float offsetY;
    float originOffestX;
    float originOffestY;

    float downX;
    float downY;

    int trackingPointerId;

    {

        mPaint.setColor(Color.BLUE);

    }

    public MultitouchView21(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Utils.getAvatar(getResources(), IMAGE_WIDTH);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                trackingPointerId = event.getPointerId(0);

                downX = event.getX();
                downY = event.getY();

                originOffestX = offsetX;
                originOffestY = offsetY;


                break;

            case MotionEvent.ACTION_POINTER_DOWN:

                int actionIndex = event.getActionIndex();
                trackingPointerId = event.getPointerId(actionIndex);

                downX = event.getX(actionIndex);
                downY = event.getY(actionIndex);

                originOffestX = offsetX;
                originOffestY = offsetY;

                break;

            case MotionEvent.ACTION_MOVE:

                int index = event.findPointerIndex(trackingPointerId);
                offsetX = event.getX(index) - downX + originOffestX;
                offsetY = event.getY(index) - downY + originOffestY;
                invalidate();
                break;

            case MotionEvent.ACTION_POINTER_UP:

                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (pointerId == trackingPointerId) {
                    int newIndex;
                    if (actionIndex == event.getPointerCount() - 1) {
                        newIndex = event.getPointerCount() - 2;

                    } else {
                        newIndex = event.getPointerCount() - 1;
                    }

                    trackingPointerId = event.getPointerId(newIndex);
                    downX = event.getX(newIndex);
                    downY = event.getY(newIndex);
                    originOffestX = offsetX;
                    originOffestY = offsetY;
                }

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
