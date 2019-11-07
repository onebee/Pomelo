package one.diao.com.a13_multi_touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
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
    //    Path path = new Path();
    SparseArray<Path> paths = new SparseArray<>();

    {

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(4));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

    }

    public MultitouchView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                Path path = new Path();
                int actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);

                path.moveTo(event.getX(actionIndex), event.getY(actionIndex));
                paths.append(pointerId, path);
                invalidate();

                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    pointerId = event.getPointerId(i);
                    path = paths.get(pointerId);
                    path.lineTo(event.getX(i), event.getY(i));
                }
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:

                // 删除抬起的手指
                pointerId = event.getPointerId(event.getActionIndex());
                paths.remove(pointerId);
                invalidate();
                break;

        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.valueAt(i);
            canvas.drawPath(path, paint);
        }
    }
}
