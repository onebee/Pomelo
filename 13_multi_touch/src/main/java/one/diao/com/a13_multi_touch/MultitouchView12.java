package one.diao.com.a13_multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-08-18.
 */
public class MultitouchView12 extends View {

    public static final String TAG = "MultitouchView12";

    private final int IMAGE_WIDTH;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    Bitmap mBitmap;
    float offsetX;
    float offsetY;
    float downX;
    float downY;
    float originOffsetX;

    float originOffsetY;
    private int trackingPointerId;

    public MultitouchView12(Context context, AttributeSet attrs) {
        super(context, attrs);

        IMAGE_WIDTH = (int) Utils.dp2px(200);
        mBitmap = Utils.getAvatar(getResources(), IMAGE_WIDTH);
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

                trackingPointerId = event.getPointerId(0);

                downX = event.getX();
                downY = event.getY();

                Log.i(TAG, "action_down : " + "  downX : " + downX + "  downY : " + downY);

                originOffsetX = offsetX;
                originOffsetY = offsetY;


                break;
            case MotionEvent.ACTION_MOVE:

//                int index = event.findPointerIndex(trackingPointerId);
//
//                // 初始偏移加上按下去的 偏移
                offsetX = originOffsetX + event.getX() - downX;
                offsetY = originOffsetY + event.getY() - downY;
//                offsetX = event.getX() - downX;
//                offsetY = event.getY() - downY;
                invalidate();
                Log.i(TAG, "action_move : " + "  offsetX : " + offsetX + "  offsetY : " + offsetY);


                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                Log.i(TAG, " actionIndex  pointer down : " + actionIndex);
//                trackingPointerId = event.getPointerId(actionIndex);
//                downX = event.getX(actionIndex);
//                downY = event.getY(actionIndex);
//
//                originOffsetX = offsetX;
//                originOffsetY = offsetY;



                break;

            case MotionEvent.ACTION_POINTER_UP:
                int actionIndex1 = event.getActionIndex();
                Log.i(TAG, " actionIndex  pointer up : " + actionIndex1);


                break;

        }
        return true;
    }

}
