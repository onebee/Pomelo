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
 * @author diaokaibin@gmail.com on 2019/2/6.
 */
public class MultitouchView1 extends View {

    private static final float IMAGE_WITHD = Utils.dp2px(200);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    float offsetX,offsetY;
    float originOffsetX,originOffsetY;
    float downX,downY;


    public MultitouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WITHD);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();

                originOffsetX = offsetX;
                originOffsetY = offsetY;

                break;

            case MotionEvent.ACTION_MOVE:

                offsetX = originOffsetX + event.getX()- downX;
                offsetY = originOffsetY + event.getY() - downY;
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);

    }
}
