package one.diao.com.a13_multi_touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-08-26.
 * 多指触控 互不干扰型
 */
public class MultitouchView23 extends View {


    SparseArray<Path> paths = new SparseArray<>();

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    {
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(Utils.dp2px(5));
        mPaint.setStyle(Paint.Style.STROKE);
    }


    public MultitouchView23(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                int actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);

                Path path = new Path();
                path.moveTo(event.getX(actionIndex), event.getY());
                paths.append(pointerId, path);
                invalidate();

                break;

            case MotionEvent.ACTION_MOVE:

                int count = event.getPointerCount();
                for (int i = 0; i < count; i++) {
                    int eventPointerId = event.getPointerId(i);
                    path = paths.get(eventPointerId);
                    path.lineTo(event.getX(i), event.getY(i));

                }

                invalidate();
                break;


            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                pointerId = event.getPointerId(actionIndex);
                paths.remove(pointerId);
                invalidate();

                break;

        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.valueAt(i);
            canvas.drawPath(path, mPaint);
        }


    }
}
