package one.diao.com.a13_multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-08-18.
 */
public class MultitouchView11 extends View {

    private final int IMAGE_WITDH;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    Bitmap mBitmap;
    float offsetX;
    float offsetY;
    float downX;
    float downY;
    float orginOffsetX;
    float orginOffsetY;

    int trackingPointeId;

    public MultitouchView11(Context context, AttributeSet attrs) {
        super(context, attrs);

        IMAGE_WITDH = (int) Utils.dp2px(200);
        mBitmap = Utils.getAvatar(getResources(), IMAGE_WITDH);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                trackingPointeId = event.getPointerId(0);


                downX = event.getX();
                downY = event.getY();

                orginOffsetX = offsetX;
                orginOffsetY = offsetY;

                break;
            case MotionEvent.ACTION_MOVE:

                int index = event.findPointerIndex(trackingPointeId);

                // 初始偏移加上按下去的 偏移
                offsetX = orginOffsetX + event.getX(index) - downX;
                offsetY = orginOffsetY + event.getY(index) - downY;
                invalidate();


                break;

            case MotionEvent.ACTION_POINTER_DOWN:

                int actionIndex = event.getActionIndex();

                // 多指中  拿到刚刚按下手指的ID
                trackingPointeId = event.getPointerId(actionIndex);

                downX = event.getX(actionIndex);
                downY = event.getY(actionIndex);

                orginOffsetX = offsetX;
                orginOffsetY = offsetY;
                break;

            case MotionEvent.ACTION_POINTER_UP:

                // 获取抬起手指的ID
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (pointerId == trackingPointeId) {
                    if (actionIndex == event.getPointerCount() - 1) {
                        index = event.getPointerCount() - 2;
                    } else {
                        index = event.getPointerCount() - 1;

                    }
                    // 找到之后 记录ID
                    trackingPointeId = event.getPointerId(actionIndex);

                    downX = event.getX(index);
                    downY = event.getY(index);

                    orginOffsetX = offsetX;
                    orginOffsetY = offsetY;

                }

                break;
        }
        return true;
    }

}
